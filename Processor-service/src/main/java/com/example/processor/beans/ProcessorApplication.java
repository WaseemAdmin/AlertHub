package com.example.processor.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.processor.beans.feign")
public class ProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }
}
