package com.wujk.springbootactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
@EnableJms
public class SpringBootActiveMQConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActiveMQConsumerApplication.class, args);
    }

    @JmsListener(destination = "spring.boot.queue")
    public void consumerQueue(String msg) {
        System.out.println("consumerQueue: " + msg);
    }

    @JmsListener(destination = "spring.boot.topic", containerFactory="msgFactoryTopic")
    public void consumerTopic(String msg) {
        System.out.println("consumerTopic: " + msg);
    }
}
