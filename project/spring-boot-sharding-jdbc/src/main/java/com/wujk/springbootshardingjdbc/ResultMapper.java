package com.wujk.springbootshardingjdbc;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResultMapper {

    Integer insert(Result result);

    List<Result> list();

}
