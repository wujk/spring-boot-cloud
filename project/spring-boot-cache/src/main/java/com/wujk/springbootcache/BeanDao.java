package com.wujk.springbootcache;

import org.springframework.stereotype.Repository;

@Repository
public class BeanDao implements IBeanDao {
    @Override
    public Bean getBean(Integer id) {
        System.out.println("dao fetch");
        Bean bean = new Bean();
        bean.setId(id);
        bean.setName(id + Math.random() + "");
        return bean;
    }
}
