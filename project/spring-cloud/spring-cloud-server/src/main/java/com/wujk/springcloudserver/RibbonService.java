package com.wujk.springcloudserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonService {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "ribbon", method = RequestMethod.GET)
    public String ribbon(@RequestParam("name") String name) {
        System.out.println("Hello, " + name + ", the port is " + port);
        return "Hello, " + name + ", the port is " + port;
    }

}
