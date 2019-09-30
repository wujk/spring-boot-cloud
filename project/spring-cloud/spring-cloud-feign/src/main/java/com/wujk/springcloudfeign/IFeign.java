package com.wujk.springcloudfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-server")
public interface IFeign {

    @RequestMapping(value = "/ribbon",method = RequestMethod.GET)
    public String feign(@RequestParam(value = "name") String name);
}
