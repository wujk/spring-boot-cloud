package com.wujk.springcloudhystrixdashboard;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
public class HystrixDashboardAnotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardAnotherApplication.class, args);
    }

    @GetMapping("hello")
    @HystrixCommand
    public String hello(@RequestParam(value = "name", defaultValue = "majf") String name) {
        return "hello " + name;
    }

}
