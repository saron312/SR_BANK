package com.project.mini.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Table(name="bank_account")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long aId;

    @Column(length = 30 ,nullable = false, unique = true)
    private String accountNumber;

    @Column (length = 20, nullable = false)
    private String bankName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name = "m_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member mId;


}
