package com.scrib.scrib.appuser;

import com.google.common.collect.Sets;

import static com.scrib.scrib.appuser.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    ADMIN(String.valueOf(Sets.newHashSet(USER_AUTHORITIES
            ,USER_HANDLER_AUTHORITIES
            ,USER_ADMIN_AUTHORITIES)))
    , BLOGGER(String.valueOf(Sets.newHashSet(USER_AUTHORITIES
            ,USER_HANDLER_AUTHORITIES)))
    ,ANNOTATOR(String.valueOf(Sets.newHashSet(USER_AUTHORITIES
            ,USER_HANDLER_AUTHORITIES)));

    private final String[] permissions;

    ApplicationUserRole(String... permissions) {
        this.permissions = permissions;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public String[] grantedAuthority(){
        return permissions;
    }

}
