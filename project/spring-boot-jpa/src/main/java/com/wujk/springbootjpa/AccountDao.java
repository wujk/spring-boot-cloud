package com.wujk.springbootjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao  extends JpaRepository<Account,String> {


}
