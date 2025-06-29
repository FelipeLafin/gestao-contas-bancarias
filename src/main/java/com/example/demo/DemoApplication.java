package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.ContaBancaria;
import com.example.demo.repository.ContaBancariaRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ContaBancariaRepository repo) {
        return args -> {
            repo.save(new ContaBancaria("123456", "Maria Silva", true));
            repo.save(new ContaBancaria("789012", "João Santos", true));
        };
    }
}