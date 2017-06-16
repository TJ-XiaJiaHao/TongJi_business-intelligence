package com.bi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/5/19.
 */

@SpringBootApplication
@EnableAutoConfiguration
public class BIApplication {
    public static void main(String[] args) {
        SpringApplication.run(BIApplication.class, args);
    }
}
