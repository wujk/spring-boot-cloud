package com.wujk.springcloudribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("ribbon")
    public String ribbon(@RequestParam("name") String name) {
        return restTemplate.getForObject("http://cloud-server/ribbon?name="+name,String.class);
    }
}
