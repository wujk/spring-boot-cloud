package com.wujk.springbootredis.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "send/{message}", method = RequestMethod.GET)
    public void send(@PathVariable String message) {
        System.out.println("send " + message);
        redisTemplate.convertAndSend("message", message);
    }

}
