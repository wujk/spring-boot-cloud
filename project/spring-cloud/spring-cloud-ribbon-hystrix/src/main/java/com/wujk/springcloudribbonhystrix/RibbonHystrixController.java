package com.wujk.springcloudribbonhystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonHystrixController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("ribbon")
    @HystrixCommand(fallbackMethod = "hiError")
    public String ribbon(@RequestParam("name") String name) {
        return restTemplate.getForObject("http://cloud-server/ribbon?name="+name,String.class);
    }

    public String hiError(String name) {
        return "Sorry !!!! " + name;
    }
}
