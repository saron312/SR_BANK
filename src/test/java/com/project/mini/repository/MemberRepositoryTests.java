package com.project.mini.repository;

import com.project.mini.entity.Account;
import com.project.mini.entity.Member;
import com.project.mini.entity.Transfer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.w3c.dom.ls.LSOutput;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransferRepository transferRepository;


    @Test
    @PostConstruct
    public void insert(){
        IntStream.rangeClosed(10,20).forEach(i->{
            Member member = Member.builder().userid("member"+i+i+i).password("Member@"+i+i+i)
                    .name("마바사").phone("01000"+i+"00"+i).build();
            memberRepository.save(member);
        });
    }

    @Test
    public void insert2(){
            Member member = Member.builder().userid("").password("")
                            .name("").phone("").build();
            memberRepository.save(member);
    }

    @Test
    public void findOne(){
        String userid = "saron312";
        Member findUser = memberRepository.findByUserid(userid);

        if(findUser == null){
            System.out.println("정보없음");
        }else{
            System.out.println(findUser.toString());
        }
    }

    @Test
    public void findName(){
        String userid = "member0000";
        String findName = memberRepository.selectName(userid);

        System.out.println(findName);
    }

    @Test
    public void login3(){
        String userid = "member0000";
        String password = "Member@0000";
        Member selectOne = memberRepository.findByUserid(userid);
        System.out.println(selectOne.getPassword());
        if(selectOne != null){
            System.out.println(selectOne.toString()+"ㅓㅓㅓㅓ");
        }
        if(selectOne.getPassword().equals(password)){
            System.out.println(selectOne.toString()+"ㅣㅣㅣㅣ");
        }
        System.out.println("없음");
    }

    @Test
    public void accountlistTest(){
        String userid = "member0007";
        List<Account> list = accountRepository.findByUserid(userid);

        for(Account account:list){
            System.out.println(account.toString());
        }
        System.out.println(list.size());
    }

    @Test
    public void pageAccount(){
        String userid = "member0007";
        Pageable pageable = PageRequest.of(0,3);
        Page<Account> page = accountRepository.findByUserid(userid,pageable);
        System.out.println(page.getContent());
        page.get().forEach(account ->{
            System.out.println(account.toString());
        });
    }

    @Test
    public void findAccount(){
        String accountnumber = "44150201303360";
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(0,3,sort);
        Page<Transfer> page = transferRepository.findByAccountnumber(accountnumber,pageable);
        page.get().forEach(transfer -> {
            System.out.println(transfer);
        });

    }

    @Test
    public void findAccountNum(){
        String counterparty ="44150201303406";
        List<String> list = transferRepository.findName(counterparty);
        if(list == null){
            System.out.println("없음");
        }
        System.out.println(list.toString());
    }


}
