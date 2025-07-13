package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.security;

import java.util.HashSet;
import java.util.Set;

import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.Role;
import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails {
    private final int id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> permissions;

    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.permissions = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            permissions.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

}
