package com.example.moment.service;

public interface ItemService {

    
    /**
     * 
     * 현재 사용자가 key 아이템을 이미 획득했는지
     */
    boolean isAcquiredByMe(String key, String username);

    /**
     * 내가 아직 획득하지 않았고, 
     * 다른 플레이어가 이미 key 아이템을 선점했는지
     */
    boolean isAcquiredByOthers(String key, String username);

    /**
     * key 아이템을 username 사용자가 획득 처리
     */
    void acquireItem(String key, String username);
}
