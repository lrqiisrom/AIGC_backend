package com.example.aigc_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.aigc_backend.mapper")
public class AigcBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AigcBackendApplication.class, args);
    }

}
