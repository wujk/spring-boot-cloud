package com.wujk.springcloudfeignhystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-server", fallback = FeignHystrixError.class)
public interface IFeignHystrix {

    @RequestMapping(value = "/ribbon",method = RequestMethod.GET)
    public String feign(@RequestParam(value = "name") String name);
}
