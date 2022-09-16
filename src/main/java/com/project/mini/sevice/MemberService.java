package com.project.mini.sevice;

import com.project.mini.entity.Member;
import com.project.mini.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public boolean checkUserIdDuplicate(String userid){
        return memberRepository.existsByUserid(userid);
    }

    public Member save(Member member) {
            return memberRepository.save(member);
    }

    public Member login (String userid, String password){
        Member chkMember = memberRepository.findByUserid(userid);
        if (chkMember == null){
            return null;
        } else if (!chkMember.getPassword().equals(password)){
            return null;
        }
        //System.out.println(chkMember.toString()); 로그인 확인용
        return chkMember;
    }

}
