package com.example.moment.service;

import com.example.moment.config.UserPrincipal;
import com.example.moment.entity.Progress;
import com.example.moment.entity.User;
import com.example.moment.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ProgressService progressService;    // ← inject ProgressService

    // constructor injection for both repositories/services
    public UserService(UserRepository userRepository,
                       ProgressService progressService) {
        this.userRepository = userRepository;
        this.progressService = progressService;
    }

    // 로그인용 - Spring Security에서 사용됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        // ProgressService가 제공해야 할 메서드명 예시입니다.
        Progress progress = progressService.getOrCreateByUser(user);

        return new UserPrincipal(user, progress);
    }

    // 사용자 이름으로 User 조회
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 사용자 정보 조회용
    public User getUserInfo(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보 조회 실패: " + username));
    }
}
