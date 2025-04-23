package com.example.moment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.moment.config.UserPrincipal;
import com.example.moment.entity.Item;
import com.example.moment.entity.ItemAcquisition;
import com.example.moment.entity.User;
import com.example.moment.repository.ItemAcquisitionRepository;
import com.example.moment.repository.ItemRepository;
import com.example.moment.repository.UserRepository;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemAcquisitionRepository acquisitionRepository;

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/consume")
    public ResponseEntity<Map<String, Object>> consumeItem(
            @RequestParam String key,
            @AuthenticationPrincipal UserPrincipal principal) {

        Map<String, Object> res = new HashMap<>();
        String username = principal.getUsername();

        int deleted = acquisitionRepository.deleteByItemKeyAndUsername(key, username);
        if (deleted > 0) {
            res.put("success", true);
            res.put("message", "아이템 사용 완료");
        } else {
            res.put("success", false);
            res.put("message", "보유하지 않은 아이템입니다.");
        }

        return ResponseEntity.ok(res);
    }


    @GetMapping("")
    public ResponseEntity<List<Map<String, String>>> myItems(@AuthenticationPrincipal UserPrincipal user) {
        List<ItemAcquisition> acquisitions = acquisitionRepository.findAllByUsername(user.getUsername());

        List<Map<String, String>> items = acquisitions.stream()
            .map(acq -> {
                Optional<Item> itemOpt = itemRepository.findByName(acq.getItemKey());
                Map<String, String> map = new HashMap<>();
                map.put("name", acq.getItemKey());
                map.put("description", itemOpt.map(Item::getDescription).orElse(""));
                return map;
            }).toList();

        return ResponseEntity.ok(items);
    }


    /** 1) 획득 가능 여부 체크 */
    @GetMapping("/acquired/{key}")
    public ResponseEntity<Boolean> isTaken(
            @PathVariable String key,
            @AuthenticationPrincipal UserPrincipal user) {

        Optional<Item> oi = itemRepository.findByName(key);
        if (oi.isEmpty()) {
            return ResponseEntity.ok(false);
        }
        Item item = oi.get();

        // 내가 이미 획득했으면 true
        if (acquisitionRepository.countByItemKeyAndUsername(key, user.getUsername()) > 0) {
            return ResponseEntity.ok(true);
        }
        // 제한된 아이템이면 전체 소진 여부 체크
        if (Boolean.TRUE.equals(item.getIsLimited())) {
            long used = acquisitionRepository.countByItemKey(key);
            return ResponseEntity.ok(used >= item.getMaxQuantity());
        }
        // 그 외엔 획득 가능
        return ResponseEntity.ok(false);
    }

    /** 2) 실제 획득 시도 */
    @PostMapping("/acquire")
    public ResponseEntity<Map<String,Object>> acquire(
            @RequestParam String key,
            @AuthenticationPrincipal UserPrincipal principal) {

        Map<String,Object> res = new HashMap<>();
        Optional<Item> oi = itemRepository.findByName(key);
        if (oi.isEmpty()) {
            res.put("success", false);
            res.put("message", "존재하지 않는 아이템입니다.");
            return ResponseEntity.ok(res);
        }
        Item item = oi.get();
        String username = principal.getUsername();

        // 이미 내 인벤토리에 있으면
        if (acquisitionRepository.countByItemKeyAndUsername(key, username) > 0) {
            res.put("success", false);
            res.put("message", "이미 인벤토리에 있습니다.");
            return ResponseEntity.ok(res);
        }
        // 제한된 아이템인데 모두 소진됐으면
        if (Boolean.TRUE.equals(item.getIsLimited())) {
            long total = acquisitionRepository.countByItemKey(key);
            if (total >= item.getMaxQuantity()) {
                res.put("success", false);
                res.put("message", "이미 다른 플레이어가 획득했습니다.");
                return ResponseEntity.ok(res);
            }
        }

        

        // 1) item_acquisition 테이블에 저장
        ItemAcquisition acq = new ItemAcquisition(key, username);
        acquisitionRepository.save(acq);

        // 2) user_items 조인에도 반영
        User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found"));
        user.getItems().add(item);
        userRepository.save(user);

        res.put("success", true);
        res.put("message", "획득에 성공했습니다.");
        return ResponseEntity.ok(res);
    }
}
