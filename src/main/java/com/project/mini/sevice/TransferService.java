package com.project.mini.sevice;

import com.project.mini.dto.TransferDto;
import org.springframework.data.domain.Page;
public interface TransferService {
    Page<TransferDto> transferPage(String accountNumber, int pageNum);
    Page<TransferDto> remittance(String accountNumber);
    void sendMoney(String sendAccountNumber, String counterparty, Long money);
}
