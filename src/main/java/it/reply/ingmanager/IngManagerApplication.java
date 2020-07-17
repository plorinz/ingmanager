package it.reply.ingmanager;

import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IngManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngManagerApplication.class, args);
        System.out.println("JVM STARTUP DONE: " + Instant.now());
    }
}
