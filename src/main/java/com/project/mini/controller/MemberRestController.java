package com.project.mini.controller;

import com.project.mini.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/idCheck")
    public boolean idCheck(@RequestParam String userId){
//        log.info("=========="+userId+"============");
        return memberService.checkUserIdDuplicate(userId);
    }

}
