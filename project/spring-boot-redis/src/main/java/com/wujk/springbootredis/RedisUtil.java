package com.wujk.springbootredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
