package com.wujk.spingcloudzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

    @Bean
    public PreZuul1 preZuul1() {
        return new PreZuul1();
    }

    @Bean
    public PreZuul2 preZuu2() {
        return new PreZuul2();
    }

    @Bean
    public PostZuul1 postZuul1() {
        return new PostZuul1();
    }

    @Bean
    public PostZuul2 postZuul2() {
        return new PostZuul2();
    }

    @Bean
    public RouteZuul1 routeZuul1() {
        return new RouteZuul1();
    }

    @Bean
    public RouteZuul2 routeZuul2() {
        return new RouteZuul2();
    }

    /*@Bean
    public ErrorZuul1 errorZuul1() {
        return new ErrorZuul1();
    }*/

    @Bean
    public ErrorZuul2 errorZuul2() {
        return new ErrorZuul2();
    }
}
