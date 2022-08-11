package com.example.paymentapp.service;

import com.example.paymentapp.exception.BusinessException;
import com.example.paymentapp.model.Card;
import com.example.paymentapp.model.Gpa;
import com.example.paymentapp.model.Merchant;
import com.example.paymentapp.model.Transaction;
import com.example.paymentapp.repo.CardRepository;
import com.example.paymentapp.repo.GpaRepository;
import com.example.paymentapp.repo.MerchantRepository;
import com.example.paymentapp.repo.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private GpaRepository gpaRepository;

    @Mock
    private MerchantRepository merchantRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void paymentOverLimit(){
        Card card = new Card(Long.valueOf(1), Long.valueOf(1), "123455660", "234", "12/26", "A","a,b,c,d" );
        Gpa userGpa = new Gpa(Long.valueOf(1), 100.00);
        Merchant merchant = new Merchant(Long.valueOf(1), "a", "IKEA", Long.valueOf(5) );

        given(cardRepository.findById(Long.valueOf(1))).willReturn(Optional.of(card));
        given(gpaRepository.findById(Long.valueOf(1))).willReturn(Optional.of(userGpa));
        given(merchantRepository.findById(Long.valueOf(1))).willReturn(Optional.of(merchant));

        BusinessException overLimitException = org.junit.jupiter.api.Assertions.assertThrows(BusinessException.class, () -> {
            transactionService.pay(Long.valueOf(1), 500.00, Long.valueOf(1));;
        });

        assertEquals("Current balance is not enough for this payment.", overLimitException.getMessage());

    }

    @Test
    public void mccDoesNotMatch(){
        Card card = new Card(Long.valueOf(1), Long.valueOf(1), "123455660", "234", "12/26", "A","a,b,c,d" );
        Gpa userGpa = new Gpa(Long.valueOf(1), 1000.00);
        Merchant merchant = new Merchant(Long.valueOf(1), "x", "IKEA", Long.valueOf(5) );

        given(cardRepository.findById(Long.valueOf(1))).willReturn(Optional.of(card));
        given(gpaRepository.findById(Long.valueOf(1))).willReturn(Optional.of(userGpa));
        given(merchantRepository.findById(Long.valueOf(1))).willReturn(Optional.of(merchant));

        BusinessException overLimitException = org.junit.jupiter.api.Assertions.assertThrows(BusinessException.class, () -> {
            transactionService.pay(Long.valueOf(1), 500.00, Long.valueOf(1));;
        });

        assertEquals("This card is not allowed to pay this merchant.", overLimitException.getMessage());

    }

    @Test
    public void successfullPayment() throws Exception {

        Card card = new Card(Long.valueOf(1), Long.valueOf(1), "123455660", "234", "12/26", "A","a,b,c,d" );
        Gpa userGpa = new Gpa(Long.valueOf(1), 1000.00);
        Gpa merchantGpa = new Gpa(Long.valueOf(5), 500.00);
        Merchant merchant = new Merchant(Long.valueOf(1), "a", "IKEA", Long.valueOf(5) );

        given(cardRepository.findById(Long.valueOf(1))).willReturn(Optional.of(card));
        given(gpaRepository.findById(Long.valueOf(1))).willReturn(Optional.of(userGpa));
        given(merchantRepository.findById(Long.valueOf(1))).willReturn(Optional.of(merchant));
        given(gpaRepository.findById(Long.valueOf(5))).willReturn(Optional.of(merchantGpa));

        Transaction transaction = transactionService.pay(Long.valueOf(1), 100.0, Long.valueOf(1));

        given(transactionRepository.findById(Long.valueOf(1))).willReturn(Optional.of(transaction));

        Gpa newMerchantGpa = gpaRepository.findById(Long.valueOf(5)).orElseThrow();
        Gpa userNewGpa = gpaRepository.findById(Long.valueOf(1)).orElseThrow();
        Transaction transactionRecord = transactionRepository.findById(Long.valueOf(1)).orElseThrow();

        assertEquals(900.00, userNewGpa.getBalance());
        assertEquals(600.00, newMerchantGpa.getBalance());
        assertEquals(100.0, transactionRecord.getTransactionAmount());



    }
}
