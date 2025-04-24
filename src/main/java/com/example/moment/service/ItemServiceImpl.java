package com.example.moment.service;

import com.example.moment.entity.Item;
import com.example.moment.entity.ItemAcquisition;
import com.example.moment.repository.ItemAcquisitionRepository;
import com.example.moment.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemAcquisitionRepository acquisitionRepo;

    @Autowired
    private ItemRepository itemRepo;

    @Override
    public boolean isAcquiredByMe(String key, String username) {
        return acquisitionRepo.existsByUsernameAndItemKey(username, key);
    }

    @Override
    public boolean isAcquiredByOthers(String key, String username) {
        // 이미 내가 획득한 아이템이면 false
        if (acquisitionRepo.existsByUsernameAndItemKey(username, key)) {
            return false;
        }
        // 다른 사람이 한 명이라도 획득했으면 true
        return acquisitionRepo.countByItemKey(key) > 0;
    }

    /**
     * key 아이템을 username 사용자가 획득 처리
     *  - “기억” 카테고리라면 사용자당 최대 2개까지
     *  - isLimited==true 아이템은 maxQuantity까지
     *  - 중복 획득 방지
     */
    @Override
    @Transactional
    public void acquireItem(String key, String username) {
        // 1) Item 엔티티 조회
        Item item = itemRepo.findByName(key)
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 아이템 키: " + key));

        // 1.1) “기억” 카테고리 아이템은 사용자당 최대 2개까지
        if ("기억".equals(item.getCategory())) {
            long already = acquisitionRepo.findAllByUsername(username).stream()
                .map(ItemAcquisition::getItemKey)
                .map(itemRepo::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(i -> "기억".equals(i.getCategory()))
                .count();
            if (already >= 2) {
                throw new IllegalStateException(
                    String.format("‘기억’ 카테고리 아이템은 사용자당 최대 %d개까지 획득 가능합니다.", 2)
                );
            }
        }

        // 2) isLimited=true 인 경우, maxQuantity 검사
        if (Boolean.TRUE.equals(item.getIsLimited())) {
            long count = acquisitionRepo.countByItemKeyAndUsername(key, username);
            if (count >= item.getMaxQuantity()) {
                throw new IllegalStateException(
                    String.format("아이템 ‘%s’은(는) 최대 %d개까지 획득 가능합니다.", 
                                  key, item.getMaxQuantity())
                );
            }
        }

        // 3) 중복 획득 방지
        if (acquisitionRepo.existsByUsernameAndItemKey(username, key)) {
            throw new IllegalStateException("이미 획득한 아이템입니다: " + key);
        }

        // 4) 획득 처리
        ItemAcquisition acq = new ItemAcquisition(key, username);
        acquisitionRepo.save(acq);
    }
}
