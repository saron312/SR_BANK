package com.project.mini.controller;

import com.project.mini.dto.AccountDto;
import com.project.mini.sevice.AccountService;
import com.project.mini.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequiredArgsConstructor

@Log4j2
public class HomeController {

    private final MemberService memberService;
    private final AccountService accountService;

    @GetMapping("/")
    public String index(Principal principal, Model model){
        if(principal != null){
            Page<AccountDto> accountDto = accountService.userAccountList(principal.getName(), 0);
            model.addAttribute("page", accountDto);
            return "index";
        }
        return "index";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("member",memberService.loadAllMember());
        return "admin";}



}

