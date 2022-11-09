package com.project.mini.domain.repository;


import com.project.mini.domain.entity.Account;
import com.project.mini.domain.entity.Member;
import com.project.mini.dto.AccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /* 계좌번호만 가져오면 되기 때문에 String으로 List 만들었음.. 나중에 컬럼이 추가된다면
       Account로 선언 후 불러올 때 account.*** 로 사용해야함
       Account로 쓰니까..account.getAccountNumeber로만 가져와짐..당연함 객체임.. ;;; */
    @Query(value = "select a.accountNumber from Account a where a.mId.userId = :userId and a.bankName = :bankName" )
    List<String> findSrBankList(String userId,String bankName);


    @Query(value = "select a from Account a join fetch a.mId where a.mId.userId = :userId "
    ,countQuery = "select count(a) FROM Account a WHERE a.mId.userId = :userId")
    Page<Account> findAccountPage(String userId, Pageable pageable);

    boolean existsByAccountNumber(String accountNumber);

    @Query(value = "select a.bankName from Account a where a.accountNumber = :accountNumber")
    String findBankName (String accountNumber);
}
