package com.wujk.springbootcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    IBeanDao beanDao;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @Cacheable("cache")
    public Bean getBean(@PathVariable("id") Integer id) {
        return beanDao.getBean(id);
    }
}
