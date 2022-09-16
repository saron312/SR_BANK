package com.project.mini.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="bank_accountlist")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String accountnumber;

    @Column (nullable = false)
    private String bankname;

    @Column (nullable = false)
    private String userid;


}
