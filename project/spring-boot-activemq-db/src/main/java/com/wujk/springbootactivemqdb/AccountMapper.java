package com.wujk.springbootactivemqdb;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

     int insert(Account account);

     int insertSelective(Account account);

}
