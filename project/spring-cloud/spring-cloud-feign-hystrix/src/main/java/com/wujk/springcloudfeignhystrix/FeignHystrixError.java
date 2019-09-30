package com.wujk.springcloudfeignhystrix;

import org.springframework.stereotype.Component;

@Component
public class FeignHystrixError implements IFeignHystrix{
    @Override
    public String feign(String name) {
        return "Sorry !!! " + name;
    }
}
