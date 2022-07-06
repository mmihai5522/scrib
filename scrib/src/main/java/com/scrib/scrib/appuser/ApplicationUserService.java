package com.scrib.scrib.appuser;



import com.scrib.scrib.config.LoginAttemptCacheService;
import com.scrib.scrib.exception.domain.EmailExistException;
import com.scrib.scrib.exception.domain.UserNameExistsException;
import com.scrib.scrib.exception.domain.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.scrib.scrib.constant.ApplicationUserConstant.*;


@Service
@Transactional
@Slf4j
@Qualifier("userDetailsService")
public class ApplicationUserService implements UserDetailsService {

    private Logger LOGGER= LoggerFactory.getLogger(ApplicationUserService.class);
    private final ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptCacheService loginAttemptCacheService;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository applicationUserRepository
            , BCryptPasswordEncoder passwordEncoder, LoginAttemptCacheService loginAttemptCacheService) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptCacheService = loginAttemptCacheService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository
                .findApplicationUserByUserName(username);
        if (applicationUser == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME+ username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempt(applicationUser);
            applicationUser.setLastLoginDateDisplay(applicationUser.getLastLoginDate());
            applicationUser.setLastLoginDate(new Date());
            applicationUserRepository.save(applicationUser);
            ApplicationUserPrincipal applicationUserPrincipal =
                    new ApplicationUserPrincipal(applicationUser);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
            return applicationUserPrincipal;
        }
    }

    private void validateLoginAttempt(ApplicationUser applicationUser) {
        if (applicationUser.getNotLocked()){
            if (loginAttemptCacheService.hasExceededMaxAttempt(applicationUser.getUserName())){
                applicationUser.setNotLocked(false);
            }else {
                applicationUser.setNotLocked(true);
            }
        }else {
            loginAttemptCacheService.evictFromCache(applicationUser.getUserName());
        }
    }

    public ApplicationUser findApplicationUserByEmail(String email){
        return  applicationUserRepository.findApplicationUserByEmail(email);}


    public ApplicationUser findApplicationUserByUserName(String username){
        return  applicationUserRepository.findApplicationUserByUserName(username);}


    public List<ApplicationUser> getUsers(){
        return applicationUserRepository.findAll();
    }

    public ApplicationUser register(String firstName
            , String lastName, String userName, String email)
            throws UserNotFoundException, EmailExistException, UserNameExistsException {

         validatenewApplicationUserNameandEmail(EMPTY, userName, email);
        ApplicationUser user = new ApplicationUser();
         user.setUserId(setApplicationUserId());
         String password = generatePassword();
         String encodedPassword = encodePassword(password);
         user.setFirstName(firstName);
         user.setLastName(lastName);
         user.setUserName(userName);
         user.setEmail(email);
         user.setJoinDate(new Date());
         user.setPassword(encodedPassword);
         user.setEnabled(true);
         user.setNotLocked(false);
         user.setApplicationUserRole(ApplicationUserRole.valueOf(ApplicationUserRole.ANNOTATOR.name()));
         user.setAuthorities(ApplicationUserRole.ANNOTATOR.getPermissions());
         user.setImageUrl(getTemporaryProfileImageUrl());
         LOGGER.info("new User: " + "\r" + password + "\r");
         applicationUserRepository.save(user);

        return user;
    }



    public ApplicationUser validatenewApplicationUserNameandEmail
            (String currentUsername, String newUsername, String newEmail)
            throws UsernameNotFoundException, UserNotFoundException, UserNameExistsException, EmailExistException {

        ApplicationUser userByNewUsername = applicationUserRepository.findApplicationUserByUserName(newUsername);
        ApplicationUser userByNewEmail = applicationUserRepository.findApplicationUserByEmail(newEmail);


        if(StringUtils.isNotBlank(currentUsername)) {
            ApplicationUser currentUser = applicationUserRepository.findApplicationUserByUserName(currentUsername);
            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UserNameExistsException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if(userByNewUsername != null) {
                throw new UserNameExistsException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

    private String setApplicationUserId() {
        return UUID.randomUUID().toString();
    }

    private String getTemporaryProfileImageUrl() {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH).toUriString();

    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
    private String generatePassword() {return RandomStringUtils.randomAlphanumeric(10);}

}


