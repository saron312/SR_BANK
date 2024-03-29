package com.project.mini.domain.repository;

import com.project.mini.domain.entity.Account;
import com.project.mini.domain.entity.Transfer;
import com.project.mini.dto.TransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    /* 입출금 내역 ..^^....뽑기...join ㅇ ㅓ렵 ㄷ ㅏ...흑ㅎㄱㅎ긓긓.. */
    @Query(value = "select t from Transfer t join fetch t.mId where t.aId.accountNumber = :accountNumber order by t.dwDate desc"
    , countQuery = "select count(t) FROM Transfer t WHERE t.aId.accountNumber = :accountNumber")
    Page<Transfer> findDwList(String accountNumber, Pageable pageable);

//    @Query(value = "select t from Transfer t where t.dw='출금' and t.aId.accountNumber=:accountNumber order by t.dwDate desc")
    @Query(value = "select * from bank_transfer where dw='출금' and (a_id,dw_date) in (select 1, max(t.dw_date) from bank_transfer t group by t.a_id) order by dw_date desc", nativeQuery = true)
    Page<Transfer> remittance(String accountNumber,Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "insert into bank_transfer(counterparty,dw,money,a_id,m_id,total)values" +
            "(:counterparty,'출금',:money," +
            "(select a_id from bank_account where account_number=:accountNumber)," +
            "(select m_id from bank_account where account_number=:counterparty)," +
            "(select total from bank_transfer t where a_id = (select a_id from bank_account where account_number=:accountNumber) order by dw_date desc limit 1)-:money)," +
            "(:accountNumber,'입금',:money," +
            "(select a_id from bank_account where account_number=:counterparty)," +
            "(select m_id from bank_account where account_number=:accountNumber)," +
            "(select total from bank_transfer t where a_id =(select a_id from bank_account where account_number=:counterparty) order by dw_date desc limit 1)+:money);", nativeQuery = true)
    int saveTransfer(String accountNumber, String counterparty, Long money);
}
