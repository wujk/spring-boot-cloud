package com.wujk.springbootshardingjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShardingJdbcController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("insert")
    @Transactional
    public int insert(@RequestBody User user) {
        return userMapper.insert(user);
    }

    @GetMapping("list")
    public List<User> list() {
        return userMapper.list();
    }

}
