package com.wujk.springbootdubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.wujk.base.IDubboService;

@Service
public class DubboService implements IDubboService {

    @Override
    public String hello(String name) {
        return "hello, " + name;
    }
}
