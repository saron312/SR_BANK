package com.project.mini.repository;

import com.project.mini.entity.Account;
import com.project.mini.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Date> {

    //List<Transfer> findByAccountnumber(String accountnumber);

    Page<Transfer> findByAccountnumber(String accountnumber, Pageable pageable);

    @Query("select m.name, a.bankname from Member m, Account a, Transfer t where m.userid = a.userid and a.accountnumber = t.accountnumber and t.accountnumber = :counterparty")
    public List<String> findName(String counterparty);
}
