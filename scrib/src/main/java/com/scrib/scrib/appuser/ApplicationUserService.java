package com.scrib.scrib.appuser;


import com.scrib.scrib.role.ApplicationRoleRepository;
import com.scrib.scrib.role.ApplicationUserRole;
import com.scrib.scrib.security.CustomEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ApplicationUserService implements UserDetailsService {

    private final CustomEncoder customEncoder;

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationRoleRepository applicationRoleRepository;


    public ApplicationUser saveUser(ApplicationUser applicationUser) {
        log.info("save");
        applicationUser.setPassword(customEncoder.bCryptEncoder().encode(applicationUser.getPassword()));
        return applicationUserRepository.save(applicationUser);
    }


    public ApplicationUserRole saveRole(ApplicationUserRole applicationUserRole) {
        log.info("saveRole");
        return applicationRoleRepository.save(applicationUserRole);
    }


    public void addRoleToUser(String usermane, String roleName) {

        ApplicationUser user=applicationUserRepository.findApplicationUserByEmail(usermane);
        ApplicationUserRole role=applicationRoleRepository.findBy(roleName);
        log.info("role2user");
        user.getApplicationUserRole().add(role);

    }


    public ApplicationUser getUser(String username) {
        log.info("getuser");
        return applicationUserRepository.findApplicationUserByEmail(username);
    }


    public List<ApplicationUser> getUsers() {
        log.info("all");
        return applicationUserRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("getuser");
        ApplicationUser applicationUser=
                applicationUserRepository.findApplicationUserByEmail(username);
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();

        applicationUser.getApplicationUserRole().forEach(role -> {
            authorities.add(
                    new SimpleGrantedAuthority(role.getName()));
                }
        );

        return new User(applicationUser.getUsername()
                ,applicationUser.getPassword()
                ,authorities);
    }
}


