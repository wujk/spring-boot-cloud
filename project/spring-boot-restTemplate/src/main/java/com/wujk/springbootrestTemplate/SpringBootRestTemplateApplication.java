package com.wujk.springbootrestTemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootRestTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestTemplateApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
        return args -> {
            String quote = restTemplate.getForObject(
                    "http://localhost:8083/info", String.class);
            System.out.println(quote);
        };
    }
}
