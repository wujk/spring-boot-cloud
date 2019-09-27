package com.wujk.springbootredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/hello")
    public String hello() {
        return redisUtil.get("wx7eac9bab6bd31f83access_token");
    }
}
