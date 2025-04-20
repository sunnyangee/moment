// src/main/java/com/example/moment/service/ProgressServiceImpl.java
package com.example.moment.service;

import com.example.moment.entity.Progress;
import com.example.moment.entity.User;
import com.example.moment.repository.ProgressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {
    private final ProgressRepository repo;
    public ProgressServiceImpl(ProgressRepository repo) {
        this.repo = repo;
    }

    @Override
    public Progress getOrCreateByUser(User user) {
        return repo.findByUser(user)
                   .orElseGet(() -> repo.save(new Progress(user, "start")));
    }

    @Override
    public Progress updateStage(User user, String stage) {
        Progress p = repo.findByUser(user)
                         .orElse(new Progress(user, stage));
        p.setStage(stage);
        return repo.save(p);
    }

    // 새로 추가
    @Override
    public Progress findByUser(User user) {
        return repo.findByUser(user)
                   .orElseThrow(() -> new IllegalStateException("진행 상태가 없습니다"));
    }
}
