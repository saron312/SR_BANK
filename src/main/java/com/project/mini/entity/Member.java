package com.project.mini.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name="bank_member")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String userid;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String phone;
}
