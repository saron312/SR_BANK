package com.project.mini.domain.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Getter
@Table(name="bank_transfer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long tId;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name="a_id")
    private Account aId;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name="m_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member mId;

    @Column (length = 10 ,nullable = false)
    private String dw;

    @Column (nullable = false)
    @ColumnDefault("0")
    private Long money;

    @Column (length = 30, nullable = false)
    private String counterparty;

    @CreationTimestamp
    private Timestamp dwDate;

    @Column (nullable = false)
    private Long total;


}

