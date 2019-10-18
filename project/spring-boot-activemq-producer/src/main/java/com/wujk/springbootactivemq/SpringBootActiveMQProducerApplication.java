package com.wujk.springbootactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootApplication
@RestController
public class SpringBootActiveMQProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActiveMQProducerApplication.class, args);
    }

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Autowired
    Topic topic;

    @GetMapping("queue/{msg}")
    @Transactional
    public String producerQueue(@PathVariable String msg) {
        jmsTemplate.convertAndSend(queue, msg);
        int a = 1/0;
        return "queue";
    }

    @GetMapping("topic/{msg}")
    public String producerTopic(@PathVariable String msg) {
        jmsTemplate.convertAndSend(topic, msg);
        return "topic";
    }
}
