package com.example.paymentapp.service;

import com.example.paymentapp.enums.CardEnum;
import com.example.paymentapp.exception.BusinessException;
import com.example.paymentapp.exception.DataNotFoundException;
import com.example.paymentapp.model.Card;
import com.example.paymentapp.model.Gpa;
import com.example.paymentapp.model.Merchant;
import com.example.paymentapp.model.Transaction;
import com.example.paymentapp.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    GpaRepository gpaRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    CardRepository cardRepository;


    public Transaction pay(Long cardId, Double amount, Long merchantId) throws Exception {
       Card card = cardRepository.findById(cardId).orElseThrow(() -> new DataNotFoundException("Invalid card id"));
       Gpa userGpa = gpaRepository.findById(card.getGpaId()).orElseThrow(() -> new DataNotFoundException("Invalid Gpa id"));
       Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(() -> new DataNotFoundException("Invalid merchant id"));

       if(!checkMccAllowed(card.getMccList(), merchant.getMcc())){
            throw new BusinessException("This card is not allowed to pay this merchant.");
        }else if(userGpa.getBalance()<amount){
            throw new BusinessException("Current balance is not enough for this payment.");
        }else if(card.getState().equals(CardEnum.CANCELLED)){
           throw new BusinessException("Cancelled card!");
       }

       Gpa merchantGpa = gpaRepository.findById(merchant.getGpaId()).orElseThrow(() -> new DataNotFoundException("Invalid Gpa id"));;
       merchantGpa.setBalance(merchantGpa.getBalance() + amount);
       gpaRepository.save(merchantGpa);

       userGpa.setBalance(userGpa.getBalance()-amount);
       gpaRepository.save(userGpa);

        Transaction transaction = Transaction.builder().transactionAmount(amount)
                .transactionDate(LocalDateTime.now())
                .merchantId(merchant.getGpaId())
                .cardId(cardId).build();

       transactionRepository.save(transaction);

        return transaction;
    }

    private boolean checkMccAllowed(String mccList, String code){
        String[] allowedTypes = mccList.split(",");
        return Arrays.stream(allowedTypes).anyMatch(mcc -> mcc.equals(code));
    }
}
