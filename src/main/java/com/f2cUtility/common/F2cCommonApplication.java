package com.f2cUtility.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class F2cCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(F2cCommonApplication.class, args);
        System.out.println("f2c-common Service started..");
    }

}
