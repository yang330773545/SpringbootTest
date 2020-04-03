package com.yang.gzcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GzcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzcloudApplication.class, args);
    }

}
