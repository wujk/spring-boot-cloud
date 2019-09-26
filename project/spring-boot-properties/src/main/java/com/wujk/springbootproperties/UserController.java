package com.wujk.springbootproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({User.class})
public class UserController {

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private Integer age;

    @Autowired
    private User user;

    @RequestMapping("info")
    public String info() {
        return name + ":" + age;
    }

    @RequestMapping("user")
    public String user() {
        return user.toString();
    }
}
