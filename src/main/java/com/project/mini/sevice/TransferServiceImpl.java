package com.project.mini.sevice;

import com.project.mini.domain.entity.Transfer;
import com.project.mini.domain.repository.TransferRepository;
import com.project.mini.dto.TransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final AccountService accountService;

    public TransferServiceImpl(TransferRepository transferRepository, AccountService accountService) {
        this.transferRepository = transferRepository;
        this.accountService = accountService;
    }

    @Override
    public Page<TransferDto> transferPage(String accountNumber, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 3);
        Page<Transfer> transfers = transferRepository.findDwList(accountNumber,pageable);
        return transfers.map( t-> TransferDto.builder()
                .dw(t.getDw())
                .money(t.getMoney())
                .counterparty(t.getCounterparty())
                .dwDate(t.getDwDate())
                .name(t.getMId().getName())
                .total(t.getTotal())
                .bankName(accountService.findBankName(t.getCounterparty()))
                .build());
    }

    @Override
    public Page<TransferDto> remittance(String accountNumber) {
        Pageable pageable = PageRequest.of(0,5);
        Page<Transfer> page = transferRepository.remittance(accountNumber,pageable);
        return page.map( t -> TransferDto.builder()
                .counterparty(t.getCounterparty())
                .bankName(accountService.findBankName(t.getCounterparty()))
                .name(t.getMId().getName())
                .build());
    }

    @Override
    public void sendMoney(String accountNumber, String counterparty, Long money) {
        transferRepository.saveTransfer(accountNumber,counterparty,money);
    }

}
