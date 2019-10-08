package com.wujk.springbootdubboclient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wujk.base.IDubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboController {

    @Reference
    private IDubboService dubboService;

    @GetMapping("hello")
    public String hello(@RequestParam("name")String name) {
        return dubboService.hello(name);
    }

}
