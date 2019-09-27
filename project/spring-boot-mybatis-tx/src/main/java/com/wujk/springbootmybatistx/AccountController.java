package com.wujk.springbootmybatistx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountMapper accountMapper;

    @RequestMapping("insert")
    @Transactional
    public Integer insert(){
        Integer count = 0;
        Account account = new Account();
        account.setUid("1");
        count += accountMapper.insert(account);
        account = new Account();
        account.setUid("2");
        int a = 1/0;
        count += accountMapper.insertSelective(account);
        return  count;
    }


}
