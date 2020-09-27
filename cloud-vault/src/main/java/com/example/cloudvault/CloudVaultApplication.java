package com.example.cloudvault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class CloudVaultApplication {

    @Autowired
    VaultTemplate vaultTemplate;

    public static void main(String[] args) {
        SpringApplication.run(CloudVaultApplication.class, args);
    }

    @GetMapping("/demo")
    public String demo(){
        // vaultTemplate.xxx
        return null;
    }
}
