package com.wujk.springbootshardingjdbc;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfoMapper {

    Integer insert(Info pass);

    List<Info> list();

}
