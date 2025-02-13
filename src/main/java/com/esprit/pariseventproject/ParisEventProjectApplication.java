package com.esprit.pariseventproject;

import com.esprit.pariseventproject.entities.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
@EnableScheduling  // 🚀 Active les tâches planifiées

@SpringBootApplication
public class ParisEventProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParisEventProjectApplication.class, args);
    }





}
