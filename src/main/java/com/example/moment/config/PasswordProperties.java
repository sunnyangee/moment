package com.example.moment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "passwords")
public class PasswordProperties {
    /**
     * application.yml 의 passwords 아래 전부 바인딩됩니다.
     * key  = roomId
     * value = 실제 암호
     */
    
    @PostConstruct
    public void logLoaded() {
        System.out.println("🔑 Loaded password keys: " + map.keySet());
    }
    @PostConstruct
    public void logKeys() {
    System.out.println("Loaded passwords keys → " + map.keySet());
    }
    private Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
