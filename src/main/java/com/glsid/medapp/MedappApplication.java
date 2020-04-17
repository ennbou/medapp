package com.glsid.medapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MedappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedappApplication.class, args);
    }

}
