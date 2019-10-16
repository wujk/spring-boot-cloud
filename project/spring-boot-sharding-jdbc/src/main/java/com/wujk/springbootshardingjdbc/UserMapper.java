package com.wujk.springbootshardingjdbc;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer insert(User user);

    List<User> list();

}
