package com.htxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.htxx.mapper")
@SpringBootApplication
@EnableCaching //开启缓存
public class WooApplication {

    public static void main(String[] args) {
        SpringApplication.run(WooApplication.class, args);
    }

}
