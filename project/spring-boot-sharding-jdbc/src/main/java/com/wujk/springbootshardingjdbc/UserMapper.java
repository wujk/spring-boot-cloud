package com.wujk.springbootshardingjdbc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer insert(User user);

    Integer batchInsert(List<User> list);

    List<User> list();

    List<User> listByPage(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
}
