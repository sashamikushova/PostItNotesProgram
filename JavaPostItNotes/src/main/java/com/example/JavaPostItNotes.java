package com.example;


import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import(CustomMongoConfig.class)
@PWA(name = "Web Push", shortName = "Push")
@EnableScheduling
public class JavaPostItNotes implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(JavaPostItNotes.class, args);
    }
}
