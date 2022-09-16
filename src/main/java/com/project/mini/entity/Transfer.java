package com.project.mini.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name="bank_transfer")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    @Id
    private String date;

    @Column(nullable = false)
    private String accountnumber;

    @Column (nullable = false)
    private int dw;

    @Column (nullable = false)
    private long money;

    @Column (nullable = false)
    private String counterparty;


}

