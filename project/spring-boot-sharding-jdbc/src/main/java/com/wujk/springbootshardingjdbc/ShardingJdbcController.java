package com.wujk.springbootshardingjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShardingJdbcController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PassMapper passMapper;

    @Autowired
    InfoMapper infoMapper;

    @Autowired
    ResultMapper resultMapper;

    @PostMapping("user/insert")
    @Transactional
    public int insertUser(@RequestBody User user) {
        return userMapper.insert(user);
    }

    @PostMapping("user/batch/insert")
    @Transactional
    public int batchinsertUser(@RequestBody List<User> users) {
        return userMapper.batchInsert(users);
    }

    @GetMapping("user/list")
    public List<User> listUser() {
        return userMapper.list();
    }

    @GetMapping("user/list/{pageNumber}/{pageSize}")
    public List<User> listUserByPage(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        return userMapper.listByPage(pageNumber, pageSize);
    }

    @PostMapping("pass/insert")
    @Transactional
    public int insertPass(@RequestBody Pass pass) {
        return passMapper.insert(pass);
    }

    @GetMapping("pass/list")
    public List<Pass> listPass() {
        return passMapper.list();
    }

    @PostMapping("info/insert")
    @Transactional
    public int insertInfo(@RequestBody Info info) {
        return infoMapper.insert(info);
    }

    @GetMapping("info/list")
    public List<Info> listInfo() {
        return infoMapper.list();
    }

    @PostMapping("result/insert")
    @Transactional
    public int insertResult(@RequestBody Result result) {
        return resultMapper.insert(result);
    }

    @GetMapping("result/list")
    public List<Result> listResult() {
        return resultMapper.list();
    }

}
