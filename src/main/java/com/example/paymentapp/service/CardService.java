package com.example.paymentapp.service;

import com.example.paymentapp.enums.CardEnum;
import com.example.paymentapp.exception.BusinessException;
import com.example.paymentapp.exception.DataNotFoundException;
import com.example.paymentapp.model.Card;
import com.example.paymentapp.model.Gpa;
import com.example.paymentapp.model.ResponseDTO.DepositResponseDTO;
import com.example.paymentapp.repo.CardRepository;
import com.example.paymentapp.repo.GpaRepository;
import com.example.paymentapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    public CardRepository cardRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public GpaRepository gpaRepository;

    public Card saveCard(Card card){
        return cardRepository.save(card);
    }

    public Card updateCard(Long cardId, String cvv, String expiry){
        Card existing = cardRepository.findById(cardId).orElseThrow(() -> new DataNotFoundException("invalid card id"));
        if(existing.getState().equals(CardEnum.CANCELLED)){
            throw new BusinessException("Cancelled card can't be updated!");
        }
        existing.setCvv(cvv);
        existing.setExpiry(expiry);
        return cardRepository.save(existing);
    }

    public void cancelCard(Long cardId){
        Card existing = cardRepository.findById(cardId).orElseThrow(()-> new DataNotFoundException("invalid card id"));
        if(existing.getState().equals(CardEnum.CANCELLED)){
            throw new BusinessException("Card cancelled already!");
        }
        existing.setState(CardEnum.CANCELLED.getValue());
         cardRepository.save(existing);
    }

    public List<Card> getCards(Long userId){
        Long gpaId = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Invalid user id")).getGpaId();
        List<Card> cards = cardRepository.getCardsByGpaId(gpaId);
        return cards;
    }

    public DepositResponseDTO depositCard(Long cardId, Double amount){
        Long gpa_id = cardRepository.findById(cardId).orElseThrow(() -> new DataNotFoundException("Invalid card Id")).getGpaId();
        Gpa gpa = gpaRepository.findById(gpa_id).orElseThrow(() -> new DataNotFoundException("Invalid gpa Id"));
        gpa.setBalance(gpa.getBalance() + amount);
        gpaRepository.save(gpa);
        return DepositResponseDTO.builder().gpa_id(gpa.getGpaId()).balance(gpa.getBalance()).build();
    }



}
