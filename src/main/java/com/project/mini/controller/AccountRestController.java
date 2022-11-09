package com.project.mini.controller;

import com.project.mini.dto.AccountDto;
import com.project.mini.sevice.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @GetMapping("/createAccount")
    public String createAccount(String userId) {
        accountService.createAccount(userId);
        return "계좌 생성이 완료되었습니당.";
    }

    @GetMapping("/page")
    public List<AccountDto> page(int pageNum, String userid) {
        Page<AccountDto> result = accountService.userAccountList(userid, pageNum);
        return result.getContent();
    }
}
