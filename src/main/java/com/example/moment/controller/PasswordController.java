package com.example.moment.controller;

import com.example.moment.config.PasswordProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordProperties pwProps;

    @PostMapping("/check-password/{roomId}")
    public ResponseEntity<Map<String, Boolean>> checkPassword(
            @PathVariable String roomId,
            @RequestBody Map<String, String> body) {

        String real = pwProps.getMap().get(roomId);
        boolean ok = real != null && real.equals(body.get("answer"));

        return ok
            ? ResponseEntity.ok(Map.of("ok", true))
            : ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Map.of("ok", false));
    }
}
