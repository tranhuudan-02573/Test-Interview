package com.huudan.TestInterView.configs;

import org.example.MyBigNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBigNumberConfig {

    @Bean
    public MyBigNumber getMyBigNumber() {
        return new MyBigNumber();
    }
}
