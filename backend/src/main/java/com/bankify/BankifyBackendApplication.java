package com.bankify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.bankify.models") // Asegura que Spring escanee las entidades
public class BankifyBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankifyBackendApplication.class, args);
    }
}
