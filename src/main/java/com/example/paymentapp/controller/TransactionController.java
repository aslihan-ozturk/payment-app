package com.example.paymentapp.controller;

import com.example.paymentapp.model.Transaction;
import com.example.paymentapp.model.requestDTO.PaymentRequestDTO;
import com.example.paymentapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    public TransactionService transactionService;

    @PostMapping("/pay")
    public Transaction pay(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) throws Exception{
        return transactionService.pay(paymentRequestDTO.getCardId(), paymentRequestDTO.getAmount(),
                paymentRequestDTO.getMerchantId());
    }
}
