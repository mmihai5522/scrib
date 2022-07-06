package com.scrib.scrib.listener;

import com.scrib.scrib.appuser.ApplicationUser;
import com.scrib.scrib.appuser.ApplicationUserPrincipal;
import com.scrib.scrib.config.LoginAttemptCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.jaas.event.JaasAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessListener {

    private LoginAttemptCacheService attemptCacheService;


    @Autowired
    public AuthSuccessListener(LoginAttemptCacheService attemptCacheService) {
        this.attemptCacheService = attemptCacheService;
    }

    @EventListener
    public void AonAuthSuccessful(JaasAuthenticationSuccessEvent event){
        Object principal=event.getAuthentication().getPrincipal();
        if (principal instanceof ApplicationUser){
            ApplicationUserPrincipal user =(ApplicationUserPrincipal) event.getAuthentication().getPrincipal();
            attemptCacheService.evictFromCache(((ApplicationUser) principal).getUserName());
        }
    }
}
