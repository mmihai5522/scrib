package com.scrib.scrib.appuser;

public enum ApplicationUserPermission {

    USER_AUTHORITIES("user:read")
    ,USER_HANDLER_AUTHORITIES("user:update")
    ,USER_ADMIN_AUTHORITIES("user:delete");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
