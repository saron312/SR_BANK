package com.project.mini.controller;

import com.project.mini.dto.TransferDto;
import com.project.mini.sevice.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransferRestController {
    private final TransferService transferService;

    @GetMapping("/transferPage")
    public List<TransferDto> accountPage(int pageNum, String accountNumber){
        Page<TransferDto> result = transferService.transferPage(accountNumber,pageNum);
        return result.getContent();
    }
}
