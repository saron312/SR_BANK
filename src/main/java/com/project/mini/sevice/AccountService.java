package com.project.mini.sevice;


import com.project.mini.dto.AccountDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Long createAccount(String userId);

    List<AccountDto> srBankList(String userId);

    Page<AccountDto> userAccountList(String userId,int pageNum);

    String findBankName(String counterparty);

    AccountDto findMIdAId(String accountNumber);

    String findName(String accountNumber);
}
