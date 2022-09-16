package com.project.mini.sevice;

import com.project.mini.entity.Account;
import com.project.mini.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> list(String userid){
        return accountRepository.findByUserid(userid);
    }

    public Page<Account> accountPage(int pageNum, String userid){
        Pageable pageable = PageRequest.of(pageNum, 3);
        return accountRepository.findByUserid(userid, pageable);
    }

}
