package com.scrib.scrib.listener;

import com.scrib.scrib.config.LoginAttemptCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class AuthFailureListener {

    private LoginAttemptCacheService attemptCacheService;

    @Autowired
    public AuthFailureListener(LoginAttemptCacheService attemptCacheService) {
        this.attemptCacheService = attemptCacheService;
    }

    @EventListener
    public void onAuthFailure(AuthenticationFailureBadCredentialsEvent event) throws ExecutionException {

        Object princial=event.getAuthentication().getPrincipal();
        if (princial instanceof String){
            String username=(String) event.getAuthentication().getPrincipal();
            attemptCacheService.addUserToCache(username);
        }
    }

}
