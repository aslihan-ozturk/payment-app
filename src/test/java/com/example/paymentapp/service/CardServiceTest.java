package com.example.paymentapp.service;

import com.example.paymentapp.exception.BusinessException;
import com.example.paymentapp.exception.DataNotFoundException;
import com.example.paymentapp.model.Card;
import com.example.paymentapp.repo.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;


    @Test
    void depositCard_invalidId(){
        final Card card = new Card(Long.valueOf(5), Long.valueOf(1), "123456788", "245", "12/34", "A", "A,B,C");

        given(cardRepository.findById(Long.valueOf(5))).willReturn(Optional.empty());

        DataNotFoundException invalidId = org.junit.jupiter.api.Assertions.assertThrows(DataNotFoundException.class, () -> {
            cardService.depositCard(Long.valueOf(5), 500.00);;
        });

        assertEquals("Invalid card Id", invalidId.getMessage());




    }
}
