package com.testman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestmanApplication.class, args);
    }

}
