package com.project.mini.sevice;

import com.project.mini.domain.Role;
import com.project.mini.domain.entity.Member;
import com.project.mini.domain.repository.MemberRepository;
import com.project.mini.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getMId();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> memberWrapper = memberRepository.findByUserId(userId);
        Member member =memberWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin1121").equals(userId)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return new User(member.getUserId(), member.getPassword(), authorities);
    }

    public MemberDto loadMemberInfo (String userId){
        Member member = memberRepository.findByUserId(userId).get();
        return MemberDto.builder()
                .userId(member.getUserId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .timestamp(member.getTimestamp())
                .build();
    }

    public boolean checkUserIdDuplicate(String userId){
        return memberRepository.existsByUserId(userId);
    }

    /* userId로 회원정보 뽑아내기 ㅋ 나중엔 get()말고 orElse나 orElseGet, orElseThrow 를 써보자...*/
    public Member loadMId (String userId){
        return memberRepository.findByUserId(userId).get();
    }

}
