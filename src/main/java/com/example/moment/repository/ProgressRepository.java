package com.example.moment.repository;

import com.example.moment.entity.Progress;
import com.example.moment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUser(User user);
}
