package com.wujk.springcloudclientdistributed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private IFeign feign;

    @RequestMapping("hello")
    public String feign(@RequestParam("name") String name) {
        return feign.feign(name);
    }
}
