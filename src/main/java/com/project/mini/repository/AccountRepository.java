package com.project.mini.repository;


import com.project.mini.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findByUserid(String userid);
    Page<Account> findByUserid(String userid,Pageable pageable);




}
