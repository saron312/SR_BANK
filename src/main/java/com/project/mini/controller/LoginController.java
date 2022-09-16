package com.project.mini.controller;

import com.project.mini.entity.Account;
import com.project.mini.entity.Member;
import com.project.mini.sevice.AccountService;
import com.project.mini.sevice.MemberService;
import com.project.mini.sevice.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@SessionAttributes("member")
@Controller
public class LoginController {

    @Autowired
    MemberService memberService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransferService transferService;

    @GetMapping("/login")
    public String login(Model model){
        //model.addAttribute("error","");
        return "login";
    }

    @PostMapping("/login")
    public String login3(@RequestParam String userid, @RequestParam String password , Model model){
        Member selectMember = memberService.login(userid,password);
        if(selectMember == null){
            model.addAttribute("error","비밀번호를 잘못 입력했습니다. 다시 입력해주세요.");
            return "login";
        }
        model.addAttribute("member", selectMember);
        return "forward:loginok";
    }

    @PostMapping("/loginok")
    public String loginok(@ModelAttribute("member")Member member, Model model){
        model.addAttribute("member", member);
        return "loginok";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping("/home")
    public String home(@ModelAttribute("member")Member member, Model model){
        if(member.getUserid() == null){
            return "redirect:login";
        }
        model.addAttribute("member", member);
        model.addAttribute("page",accountService.accountPage(0,member.getUserid()));
        return "home";
    }

    @GetMapping("/account")
    public String account(@ModelAttribute("member")Member member, @RequestParam String accountnumber, Model model){
        model.addAttribute("member",member);
        model.addAttribute("selectAccount",accountnumber);
        model.addAttribute("page",transferService.accountPage(0,accountnumber));
        return "account";
    }




}
