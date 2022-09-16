package com.project.mini.controller;

import com.project.mini.entity.Account;
import com.project.mini.entity.Member;
import com.project.mini.entity.Transfer;
import com.project.mini.repository.MemberRepository;
import com.project.mini.sevice.AccountService;
import com.project.mini.sevice.MemberService;
import com.project.mini.sevice.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberRestController {

    @Autowired
    MemberService memberService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransferService transferService;

    @GetMapping("/idcheck")
    public boolean idCheck(@RequestParam String userid){
        return memberService.checkUserIdDuplicate(userid);
    }


    @GetMapping("/page")
    @ResponseBody
    public List<Account> page(@RequestParam int pageNum, @RequestParam String userid){
        Page<Account> result = accountService.accountPage(pageNum,userid);
        return result.getContent();
    }

    @GetMapping("/accountPage")
    @ResponseBody
    public List<Transfer> accountPage(@RequestParam int pageNum, @RequestParam String accountnumber){
        Page<Transfer> result = transferService.accountPage(pageNum,accountnumber);
        return result.getContent();
    }
}
