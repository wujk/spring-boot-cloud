package com.wujk.springbootJdbcTemplates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> list() {
        return jdbcTemplate.query("select * from ACCOUNT", new BeanPropertyRowMapper(Account.class));
    }
}
