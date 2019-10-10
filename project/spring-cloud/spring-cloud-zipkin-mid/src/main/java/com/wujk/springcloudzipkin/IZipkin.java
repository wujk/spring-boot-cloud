package com.wujk.springcloudzipkin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "zipkin-end-server")
public interface IZipkin {

    @RequestMapping(value = "/zipkin",method = RequestMethod.GET)
    public String zipkin(@RequestParam(value = "name") String name);
}
