package com.wujk.springbootrabbitMQ;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

    public void receiveMessage(String message) {
        System.out.println("receiveMessage " + message);
    }

}
