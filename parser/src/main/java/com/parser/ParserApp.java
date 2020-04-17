package com.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.commons.*" })
@EntityScan(basePackages = { "com.commons.model" })
@SpringBootApplication
public class ParserApp {

    public static void main(String[] args) {
        SpringApplication.run(ParserApp.class, args);
    }

}
