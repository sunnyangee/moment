// src/main/java/com/example/moment/service/ProgressService.java
package com.example.moment.service;

import com.example.moment.entity.Progress;
import com.example.moment.entity.User;

public interface ProgressService {
    Progress getOrCreateByUser(User user);
    Progress updateStage(User user, String stage);
    // ↳ 추가
    Progress findByUser(User user);
}


