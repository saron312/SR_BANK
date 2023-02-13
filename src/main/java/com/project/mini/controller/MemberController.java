package com.project.mini.controller;

import com.project.mini.dto.AccountDto;
import com.project.mini.dto.MemberDto;
import com.project.mini.sevice.AccountService;
import com.project.mini.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    private final AccountService accountService;

    // 회원가입 페이지
    @GetMapping("/join")
    public String join(Principal principal) {
        if(principal != null) {
            return "redirect:/";
        }
        return "join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String execJoin(MemberDto memberDto) {
        memberService.joinUser(memberDto);
        return "joinSuccess";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login(Principal principal) {
        if(principal != null) {
            return "redirect:/";
        }
            return "login";
    }

    // 로그인 결과 페이지
    @GetMapping("/login/result")
    public String loginResult() {
        return "loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String logout() {
        return "redirect:/";
    }

    // 접근 거부 페이지
    @GetMapping("/denied")
    public String denied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/info")
    public String myInfo(Principal principal, Model model) {
        MemberDto memberDto = memberService.loadMemberInfo(principal.getName());
        List<AccountDto> srList = accountService.srBankList(principal.getName());
        model.addAttribute("member",memberDto);
        model.addAttribute("srList",srList);
        return "/myinfo";
    }


}
