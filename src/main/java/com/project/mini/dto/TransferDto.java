package com.project.mini.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.mini.domain.entity.Account;
import com.project.mini.domain.entity.Member;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDto {

    private Account aId;

    private Member mId;

    private String dw;

    private Long money;

    private String counterparty;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp dwDate;

    private String name;

    private String bankName;

    private Long total;
}
