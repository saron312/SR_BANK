package com.project.mini.domain.entity;

import com.project.mini.domain.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Table(name="bank_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long mId;

    @Column(length = 20 ,nullable = false, unique = true)
    private String userId;

    @Column (length = 100, nullable = false)
    private String password;

    @Column (length = 20, nullable = false)
    private String name;

    @Column (length = 30,  nullable = false)
    private String phoneNumber;

    @CreationTimestamp
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
