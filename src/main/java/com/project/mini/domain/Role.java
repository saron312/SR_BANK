package com.project.mini.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    //MEMBER 일반회원, ADMIN 총괄관리자

    private String value;
}
