package com.lihainuo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.lihainuo.*"})
public class WebshopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopWebApplication.class, args);
    }

}
