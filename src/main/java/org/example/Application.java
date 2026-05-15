package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring Boot 启动类
 */
@SpringBootApplication
@EnableCaching // 开启缓存
@MapperScan("org.example.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("========================================");
        System.out.println("Spring Boot Application Started Successfully!");
        System.out.println("========================================");
    }
}
