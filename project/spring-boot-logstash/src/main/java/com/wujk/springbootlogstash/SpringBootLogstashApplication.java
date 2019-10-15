package com.wujk.springbootlogstash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringBootLogstashApplication {

    Random random=new Random(10000);
    private Logger logger = LoggerFactory.getLogger(SpringBootLogstashApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLogstashApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    logger.info("seed:"+random.nextInt(999999));
                }
            },100,100, TimeUnit.MILLISECONDS);

        };
    }
}
