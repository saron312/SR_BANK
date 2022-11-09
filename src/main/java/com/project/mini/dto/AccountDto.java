package com.project.mini.dto;

import com.project.mini.domain.entity.Account;
import com.project.mini.domain.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Long aId;
    private String accountNumber;
    private String bankName;
    private Member mId;

    /* DTO -> Entity */
    public Account toEntity() {
        return Account.builder()
                .aId(aId)
                .accountNumber(accountNumber)
                .bankName(bankName)
                .mId(mId)
                .build();
    }


}
