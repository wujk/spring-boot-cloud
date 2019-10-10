package com.wujk.springcloudzipkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZipkinController {

    @Autowired
    private IZipkin zipkin;

    @RequestMapping("zipkin")
    public String feign(@RequestParam("name") String name) {
        return zipkin.zipkin(name);
    }
}
