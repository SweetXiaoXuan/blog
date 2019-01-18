package com.mf.feel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.mf.feel")
@MapperScan(" com.mf.feel.model")
public class FeelApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeelApplication.class, args);
    }

}

