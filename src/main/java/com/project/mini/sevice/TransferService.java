package com.project.mini.sevice;

import com.project.mini.entity.Transfer;
import com.project.mini.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    public Page<Transfer> accountPage(int pageNum, String accountnumber){
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNum,3,sort);
        return transferRepository.findByAccountnumber(accountnumber,pageable);
    }

    public List<String> findName(String counterparty){
        return transferRepository.findName(counterparty);
    }
}
