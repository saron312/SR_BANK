package com.project.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SrMiniProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrMiniProjectApplication.class, args);
    }

}
