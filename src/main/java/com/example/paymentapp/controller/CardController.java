package com.example.paymentapp.controller;

import com.example.paymentapp.model.Card;
import com.example.paymentapp.model.ResponseDTO.DepositResponseDTO;
import com.example.paymentapp.model.requestDTO.DepositRequestDTO;
import com.example.paymentapp.model.requestDTO.UpdateCardRequestDTO;
import com.example.paymentapp.service.CardService;
import org.springframework.beans.PropertyBatchUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    public CardService cardService;

    @PostMapping("/save")
    public Card saveCard(@Valid @RequestBody Card card){
        return cardService.saveCard(card);
    }

    @PostMapping("/update")
    public Card updateCard(@Valid @RequestBody UpdateCardRequestDTO requestDTO){
        return cardService.updateCard(requestDTO.getCardId(), requestDTO.getCvv(), requestDTO.getExpiry());
    }

    @PostMapping("/cancel")
    public void cancelCard(@RequestParam("id") Long cardId){
         cardService.cancelCard(cardId);
    }

    @GetMapping("/getCards/{userId}")
    public List<Card> getCards(@PathVariable Long userId){
        return cardService.getCards(userId);
    }

    @PostMapping("/deposit")
    public DepositResponseDTO deposit(@Valid @RequestBody DepositRequestDTO requestDTO){
        return cardService.depositCard(requestDTO.getCardId(), requestDTO.getAmount());
    }


}
