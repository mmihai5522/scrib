package com.scrib.scrib.appuser;

import com.scrib.scrib.exception.ExceptionHandling;
import com.scrib.scrib.exception.domain.EmailExistException;
import com.scrib.scrib.exception.domain.UserNameExistsException;
import com.scrib.scrib.exception.domain.UserNotFoundException;
import com.scrib.scrib.utility.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

import static com.scrib.scrib.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping(path = {"/user"})
public class ApplicationUserController extends ExceptionHandling {
    private final Logger LOGGER= LoggerFactory.getLogger(ApplicationUserController.class);

    private AuthenticationManager authenticationManager;
    private ApplicationUserService applicationUserService;
    private JwtProvider jwtProvider;

    @Autowired
    public ApplicationUserController(AuthenticationManager authenticationManager, ApplicationUserService applicationUserService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<ApplicationUser> login(
            @RequestBody ApplicationUser applicationUser){

       authenticate(applicationUser.getUserName(), applicationUser.getPassword());
       ApplicationUser loggedUser= applicationUserService.findApplicationUserByUserName(applicationUser.getUserName());
       ApplicationUserPrincipal up=new ApplicationUserPrincipal(loggedUser);
        HttpHeaders jwtHeader=getJwtHeader(up);

        return new ResponseEntity<>(loggedUser,jwtHeader, OK);
    }



//    @PostMapping(path = "/register")
//    public ResponseEntity<ApplicationUser> register(
//            @RequestBody ApplicationUser applicationUser)
//            throws UserNotFoundException, EmailExistException, UserNameExistsException, MessagingException {
//
//            ApplicationUser newlyRegisteredApplicationUser=applicationUserService
//                    .usersRegister(applicationUser.getFirstName()
//            ,applicationUser.getLastName()
//            ,applicationUser.getUserName()
//            ,applicationUser.getEmail()
//            ,applicationUser.getPassword());
//
//            return new ResponseEntity<ApplicationUser>(newlyRegisteredApplicationUser, OK);
//    }

    @GetMapping (path = "/fetching")
    public ApplicationUser fetchUser(@RequestBody ApplicationUser applicationUse){
        ApplicationUser u=applicationUserService.findApplicationUserByEmail(applicationUse.getEmail());
        System.out.println(u.toString());
        return u;
    }

    @GetMapping (path = "/fetchingUN")
    public ApplicationUser fetchUserByUserName(
            @RequestBody ApplicationUser applicationUse){
        ApplicationUser u=applicationUserService.findApplicationUserByUserName(applicationUse.getUserName());
           return u;
    }

    private HttpHeaders getJwtHeader(ApplicationUserPrincipal up) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtProvider.generateJwtToken(up));
        return headers;
    }

    public void authenticate(String userName, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password));
    }


}
