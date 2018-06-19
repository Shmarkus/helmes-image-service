package com.helmes.imageservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ImageServiceApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ImageServiceApp.class)
                .web(true)
                .run(args);
    }
}
