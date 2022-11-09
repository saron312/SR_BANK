package com.project.mini.dto;

import com.project.mini.domain.Role;
import com.project.mini.domain.entity.Member;
import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto  {

    private Long mId;
    private String userId;
    private String password;
    private String name;
    private String phoneNumber;

    private Timestamp timestamp;
    private Role role;

    /* DTO -> Entity */
    public Member toEntity() {
        return Member.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .timestamp(timestamp)
                .role(role.MEMBER)
                .build();
    }




}
