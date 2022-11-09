package com.project.mini.controller;

import com.project.mini.dto.TransferDto;
import com.project.mini.sevice.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/account")
    public String dw(String accountNumber, String bankName, Model model, Principal principal){
        if(!Principal.class.isInstance(principal)){
            return "redirect:/";
        }
        Page<TransferDto> dwList = transferService.transferPage(accountNumber,0);
        model.addAttribute("dwList", dwList);
        model.addAttribute("selectAccountNumber", accountNumber);
        model.addAttribute("selectBankName" , bankName);
        return "account";
    }

    @PostMapping("/send")
    public String send(Principal principal,String accountNumber,String bankName, String total, Model model){
        if(!Principal.class.isInstance(principal)){
            return "redirect:/";
        }
        model.addAttribute("remittance",transferService.remittance(accountNumber));
        model.addAttribute("selectAccountNumber",accountNumber);
        model.addAttribute("selectBankName",bankName);
        model.addAttribute("total",total);
        return"send" ;
    }

    @PostMapping("/sendResult")
    public String sendResult(Principal principal,String sendAccountNumber, String counterparty,String sendMoney){
        if(!Principal.class.isInstance(principal)){
            return "redirect:/";
        }
        transferService.sendMoney(sendAccountNumber,counterparty,Long.valueOf(sendMoney));
        return "redirect:/";
    }
}
