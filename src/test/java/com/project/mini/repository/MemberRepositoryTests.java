package com.project.mini.repository;

import com.project.mini.domain.entity.Account;
import com.project.mini.domain.entity.Member;
import com.project.mini.domain.entity.Transfer;
import com.project.mini.domain.repository.AccountRepository;
import com.project.mini.domain.repository.MemberRepository;
import com.project.mini.domain.repository.TransferRepository;
import com.project.mini.dto.AccountDto;
import com.project.mini.dto.MemberDto;
import com.project.mini.dto.TransferDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransferRepository transferRepository;

//    @Test //빈 객체 들어가는지 테스트 = 이건 나중에 서버쪽에서도 유효성 검사를 통해 막으면 됨.
//    public void insert2() {
//        Member member = Member.builder().userid("").password("")
//                .name("").phone("").build();
//        memberRepository.save(member);
//    }

    @Test
    public void findOne() {
        String userId = "saron312";
        Member findMember = memberRepository.findByUserId(userId).get();

        if (findMember == null) {
            System.out.println("정보없음");
        } else {
            System.out.println(findMember.toString());
        }
    }

    @Test
    public void memberFindTest(){
        String userId = "member0000";
        System.out.println(memberRepository.existsByUserId(userId));
    }


//    @Test
//    public void accountlistTest() {
//        String userid = "member0007";
//        List<Account> list = accountRepository.findByUserid(userid);
//
//        for (Account account : list) {
//            System.out.println(account.toString());
//        }
//        System.out.println(list.size());
//    }
//
//    @Test
//    public void pageAccount() {
//        String userid = "member0007";
//        Pageable pageable = PageRequest.of(0, 3);
//        Page<Account> page = accountRepository.findByUserid(userid, pageable);
//        System.out.println(page.getContent());
//        page.get().forEach(account -> {
//            System.out.println(account.toString());
//        });
//    }
//
//    @Test
//    public void findAccount() {
//        String accountnumber = "44150201303360";
//        Sort sort = Sort.by("date").descending();
//        Pageable pageable = PageRequest.of(0, 3, sort);
//        Page<Transfer> page = transferRepository.findByAccountnumber(accountnumber, pageable);
//        page.get().forEach(transfer -> {
//            System.out.println(transfer);
//        });
//
//    }
//
//    @Test
//    public void findAccountNum() {
//        String counterparty = "44150201303406";
//        List<String> list = transferRepository.findName(counterparty);
//        if (list == null) {
//            System.out.println("없음");
//        }
//        System.out.println(list.toString());
//    }

    @Test
    public void insertTest(){
        String userId = "member0000";
        Member member = memberRepository.findByUserId(userId).get();
        int random = (int)(Math.random()*999)+100;
        System.out.println(random);
        String accountNumber = "441"+random+"01303"+random;
        while (accountRepository.existsByAccountNumber(accountNumber)){
            random = (int)(Math.random()*999)+100;
            accountNumber = "441"+random+"01303"+random;
        }
       AccountDto accountDto = AccountDto.builder()
                .accountNumber(accountNumber)
                .mId(member)
                .build();
        accountRepository.save(accountDto.toEntity());
    }


    @Test
    public void test(){
        Optional<Member> member = memberRepository.findByUserId("member0000");
        MemberDto memberDto = MemberDto.builder().name(member.get().getName()).build();
        System.out.println(memberDto.getName());
    }

    @Test
    public void srList(){
        List<String> list = accountRepository.findSrBankList("member0000","SR");
        list.forEach(account -> {
            System.out.println(account);
        });
    }

    @Test
    public void pageTest(){
        Pageable pageable = PageRequest.of(0,3);
        Page<Account> list = accountRepository.findAccountPage("member0000",pageable);
        list.forEach(account -> {
            System.out.println(account.getAccountNumber() +", "+account.getBankName());
        });
    }

    @Test
    public void transTest(){
        Pageable pageable = PageRequest.of(0,4);
        Page<Transfer> transferPage = transferRepository.findDwList("44199001303990",pageable);
        for(Transfer t :transferPage){
            System.out.println(t.getDwDate()+", "+t.getCounterparty()+", "+t.getMId().getName()+", "+t.getMoney());
        }
    }

    @Test
    public void transTest222(){
        Pageable pageable = PageRequest.of(0,5);
        Page<Transfer> transferPage = transferRepository.findDwList("44199001303990",pageable);
        Long aaa = 0L;
        for(Transfer t :transferPage.getContent()){
            if(t.getDw() == 1){
                aaa -= t.getMoney();
            }else{
                aaa += t.getMoney();
            }
        }
        System.out.println(aaa);
    }





}

