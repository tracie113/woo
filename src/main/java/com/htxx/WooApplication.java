package com.htxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.htxx.mapper")
@SpringBootApplication
public class WooApplication {

    public static void main(String[] args) {
        SpringApplication.run(WooApplication.class, args);
    }

}
