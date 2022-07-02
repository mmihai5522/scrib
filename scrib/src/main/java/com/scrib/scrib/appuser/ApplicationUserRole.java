package com.scrib.scrib.appuser;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Set;
import java.util.stream.Collectors;

import static com.scrib.scrib.appuser.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    ADMIN(Sets.newHashSet(USER_AUTHORITIES
            ,USER_HANDLER_AUTHORITIES
            ,USER_ADMIN_AUTHORITIES))
    , BLOGGER(Sets.newHashSet(USER_AUTHORITIES
            ,USER_HANDLER_AUTHORITIES))
    ,ANNOTATOR(Sets.newHashSet(USER_AUTHORITIES
            ,USER_HANDLER_AUTHORITIES));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthority(){
        Set<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map((permission -> new SimpleGrantedAuthority(permission.getPermission())))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
