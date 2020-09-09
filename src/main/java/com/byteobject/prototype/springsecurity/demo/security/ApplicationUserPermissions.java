package com.byteobject.prototype.springsecurity.demo.security;

public enum ApplicationUserPermissions {
    PERSON_READ_WRITE("person:read_write"),
    PERSON_READ("person:read"),
    DEPARTMENT_READ_WRITE("department:read_write"),
    DEPARTMENT_READ("department:read");

    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
