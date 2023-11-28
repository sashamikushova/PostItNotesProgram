package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CustomMongoConfig.class)
public class JavaPostItNotes {

    public static void main(String[] args) {
        SpringApplication.run(JavaPostItNotes.class, args);
    }
}
