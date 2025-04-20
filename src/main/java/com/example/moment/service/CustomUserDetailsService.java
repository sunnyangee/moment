package com.example.moment.service;

import com.example.moment.config.UserPrincipal;
import com.example.moment.entity.Progress;
import com.example.moment.entity.User;
import com.example.moment.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;
    private final ProgressService progressService;

    public CustomUserDetailsService(UserRepository userRepo,
                                    ProgressService progressService) {
        this.userRepo = userRepo;
        this.progressService = progressService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("사용자 없음: " + username));

        Progress progress = progressService.getOrCreateByUser(user);
        return new UserPrincipal(user, progress);
    }
}
