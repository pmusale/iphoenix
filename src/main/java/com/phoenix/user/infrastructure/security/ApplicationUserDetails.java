package com.phoenix.user.infrastructure.security;

import com.phoenix.user.User;
import com.phoenix.user.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ApplicationUserDetails extends org.springframework.security.core.userdetails.User { //<1>

    private static final String ROLE_PREFIX = "ROLE_";

    private final UUID userId;

    public ApplicationUserDetails(User user) {
        super(user.getEmail(), user.getPassword(), createAuthorities(user.getRoles()));
        this.userId = user.getId();
    }

    public UUID getUserId() {
        return userId;
    }

    private static Collection<SimpleGrantedAuthority> createAuthorities(Set<UserRole> roles) {
        return roles.stream()
                    .map(userRole -> new SimpleGrantedAuthority(ROLE_PREFIX + userRole.name()))
                    .collect(Collectors.toSet());
    }
}
