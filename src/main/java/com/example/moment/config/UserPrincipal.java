package com.example.moment.config;

import com.example.moment.entity.User;
import com.example.moment.entity.Progress;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final User user;
    private final Progress progress;

    public UserPrincipal(User user, Progress progress) {
        this.user = user;
        this.progress = progress;
    }

    public User getUser() {
        return user;
    }

    public Progress getProgress() {
        return progress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.getEnabled() == 1;
    }
}
