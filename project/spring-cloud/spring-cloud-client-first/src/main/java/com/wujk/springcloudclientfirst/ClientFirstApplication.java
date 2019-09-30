package com.wujk.springcloudclientfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientFirstApplication.class, args);
    }

}
