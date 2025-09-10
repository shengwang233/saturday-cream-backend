package com.saturday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement

public class SaturdayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaturdayApplication.class, args);
    }
}
