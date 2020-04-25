package com.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = { "com.commons.model" })
@SpringBootApplication
public class ParserApp {

    public static void main(String[] args) {
        SpringApplication.run(ParserApp.class, args);
    }

}
