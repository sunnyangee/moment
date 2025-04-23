package com.example.moment.service;

import com.example.moment.entity.ItemAcquisition;
import com.example.moment.repository.ItemAcquisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemAcquisitionRepository repo;

    @Override
    public boolean isAcquiredByMe(String key, String username) {
        return repo.existsByUsernameAndItemKey(username, key);
    }

    @Override
    public boolean isAcquiredByOthers(String key, String username) {
        // 내가 이미 획득했으면 false
        if (repo.existsByUsernameAndItemKey(username, key)) {
            return false;
        }
        // 내가 아니고 다른 사람이 한 명이라도 획득했으면 true
        return repo.countByItemKey(key) > 0;
    }

    @Override
    public void acquireItem(String key, String username) {
        ItemAcquisition acq = new ItemAcquisition(key, username);
        repo.save(acq);
    }
}
