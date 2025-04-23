package com.example.moment.repository;

import com.example.moment.entity.ItemAcquisition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemAcquisitionRepository extends JpaRepository<ItemAcquisition, Long> {
    // 특정 유저가 특정 아이템을 획득했는지
    boolean existsByUsernameAndItemKey(String username, String itemKey);

    // 특정 아이템을 몇 명이 획득했는지 (선착순 제한 로직에 사용)
    long countByItemKey(String itemKey);

    long countByItemKeyAndUsername(String itemKey, String username);

    List<ItemAcquisition> findAllByUsername(String username);

    List<ItemAcquisition> findByUsername(String username);

    // (필요하다면) 획득 레코드를 직접 가져올 때
    Optional<ItemAcquisition> findByUsernameAndItemKey(String username, String itemKey);

    int deleteByItemKeyAndUsername(String itemKey, String username);

}
