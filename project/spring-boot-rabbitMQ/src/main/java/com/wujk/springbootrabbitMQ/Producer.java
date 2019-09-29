package com.wujk.springbootrabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "send/{message}", method = RequestMethod.GET)
    public void send(@PathVariable String message) {
        System.out.println("send " + message);
        rabbitTemplate.convertAndSend("message", message);
    }

}
