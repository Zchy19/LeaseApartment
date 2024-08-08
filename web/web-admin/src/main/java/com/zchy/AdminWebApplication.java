package com.zchy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @projectName: lease
 * @package: com.zchy
 * @className: AdminWebApplication
 * @author: ZCH
 * @description:
 * @date: 8/3/2024 12:18 PM
 * @version: 1.0
 */
@SpringBootApplication
@EnableScheduling
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}