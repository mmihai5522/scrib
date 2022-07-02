package com.scrib.scrib.appuser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class ApplicationUserPrincipal implements UserDetails {

    ApplicationUser applicationUser;

    public ApplicationUserPrincipal(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(this.applicationUser.getApplicationUserRole().name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return this.applicationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.applicationUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.applicationUser.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.applicationUser.getEnabled();
    }
}
