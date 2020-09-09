package com.byteobject.prototype.springsecurity.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.byteobject.prototype.springsecurity.demo.security.ApplicationUserPermissions.*;

public enum ApplicationUserRoles {
    ADMIN(new HashSet<>(Arrays.asList(DEPARTMENT_READ, DEPARTMENT_READ_WRITE, PERSON_READ, PERSON_READ_WRITE))),
    USER(new HashSet<>(Arrays.asList(PERSON_READ, PERSON_READ_WRITE, DEPARTMENT_READ)));

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> collect = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toList());
        collect.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return collect;
    }
}
