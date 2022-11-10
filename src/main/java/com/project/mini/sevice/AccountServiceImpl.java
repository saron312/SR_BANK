package com.project.mini.sevice;

import com.project.mini.domain.entity.Account;
import com.project.mini.domain.repository.AccountRepository;
import com.project.mini.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final MemberService memberService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, MemberService memberService) {
        this.accountRepository = accountRepository;
        this.memberService = memberService;
    }

    /* SRBANK 계좌번호 생성하기 */
    @Override
    public Long createAccount(String userId) {
        int random = (int)(Math.random()*999)+100;

        /* 중복값 입력안되게 while문 돌리기*/
        String accountNumber = "441"+random+"-01-303"+random;
        while (accountRepository.existsByAccountNumber(accountNumber)){
            random = (int)(Math.random()*999)+100;
            accountNumber = "441"+random+"-01-303"+random;
        }
        AccountDto accountDto = AccountDto.builder()
                .accountNumber(accountNumber)
                .bankName("SR")
                .mId(memberService.loadMId(userId))
                .build();
        return accountRepository.save(accountDto.toEntity()).getAId();
    }

    @Override
    public List<AccountDto> srBankList(String userId) {
        List<String> srAccountList = accountRepository.findSrBankList(userId,"SR");
        /* Account에서 가져온 srList -> AccountDto에 담기 */
        List<AccountDto> dtoList = new ArrayList<>();
        for(String accountNumber : srAccountList){ // 3
            AccountDto dto = AccountDto.builder().accountNumber(accountNumber).build();
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public Page<AccountDto> userAccountList(String userId,int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,3);
        Page<Account> accountPage = accountRepository.findAccountPage(userId,pageable);
        return accountPage.map(m -> AccountDto.builder()
                .aId(m.getAId())
                .accountNumber(m.getAccountNumber())
                .bankName(m.getBankName())
                .build());
    }
    @Override
    public String findBankName(String counterparty) {
        return accountRepository.findBankName(counterparty);
    }

    @Override
    public AccountDto findMIdAId(String accountNumber) {
        Account account = accountRepository.findMIdAId(accountNumber).get();
        return AccountDto.builder()
                .aId(account.getAId())
                .mId(account.getMId()).build();
    }


}
