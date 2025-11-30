package com.vault.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @GetMapping("/health")
    public String healthCheck(){
        return "I'm Alive! (Vault Backend) - Profile: " + activeProfile;
    }
}
