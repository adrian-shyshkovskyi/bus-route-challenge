package com.goeuro;

import com.goeuro.factory.RoutesReader;
import com.goeuro.model.Routes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * Spring Boot application runner.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Routes getRoutes(@Value("${filePath}") String filePath) throws IOException {
        return new RoutesReader().readRoutes(filePath);
    }
}
