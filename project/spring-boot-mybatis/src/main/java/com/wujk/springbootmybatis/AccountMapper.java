package com.wujk.springbootmybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("select uid from ACCOUNT limit 1")
    public List<Account> list();
}
