package com.wujk.springbootshardingjdbc;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PassMapper {

    Integer insert(Pass pass);

    List<Pass> list();

}
