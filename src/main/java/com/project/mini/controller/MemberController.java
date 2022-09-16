package com.project.mini.controller;

import com.project.mini.entity.Member;
import com.project.mini.sevice.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    String index(){
        return "index";
    }


    @GetMapping("/join")
    public String join(){
        return "join";
    }


    @PostMapping("/join")
    public String join2(@ModelAttribute Member member){
        memberService.save(member);
        return "joinok";
    }

    @GetMapping("/joinok")
    public String joinok(){ return "joinok";}


}
