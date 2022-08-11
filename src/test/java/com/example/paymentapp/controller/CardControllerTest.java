package com.example.paymentapp.controller;

import com.example.paymentapp.model.Card;
import com.example.paymentapp.model.ResponseDTO.DepositResponseDTO;
import com.example.paymentapp.model.requestDTO.DepositRequestDTO;
import com.example.paymentapp.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
@AutoConfigureMockMvc
public class CardControllerTest {


        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CardService cardService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void getCards() throws Exception{
            Card card = new Card();
            card.setGpaId(Long.valueOf(1));
            card.setCardNumber("123123213");
            card.setCvv("345");
            card.setExpiry("11/34");
            card.setMccList("1,2,3");
            card.setState("A");

            List<Card> cardList = new ArrayList<>();
            cardList.add(card);

            given(cardService.getCards(Long.valueOf(1))).willReturn(cardList);
            mockMvc.perform(get("/card/getCards/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.[0].cardNumber").value("123123213"))
                    .andExpect(jsonPath("$.[0].gpaId").value(Long.valueOf(1)))
                    .andExpect(jsonPath("$.[0].cvv").value("345"))
                    .andExpect(jsonPath("$.[0].expiry").value("11/34"))
                    .andExpect(jsonPath("$.[0].state").value("A"))
                    .andExpect(jsonPath("$.[0].mccList").value("1,2,3"));

        }

    @Test
    public void depositCard() throws Exception{
        DepositRequestDTO requestDTO = new DepositRequestDTO();
        requestDTO.setCardId(Long.valueOf(1));
        requestDTO.setAmount(300.00);


        given(cardService.depositCard(Long.valueOf(1), 300.00)).willReturn(DepositResponseDTO.builder()
                .gpa_id(Long.valueOf(1)).balance(1300.00).build());

        mockMvc.perform(post("/card/deposit")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .content(asJsonString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("1300.0"))
                .andExpect(jsonPath("$.gpa_id").value(Long.valueOf(1)));

    }

    @Test
    public void depositCard_AmountRequired() throws Exception{
        DepositRequestDTO requestDTO = new DepositRequestDTO();
        requestDTO.setCardId(Long.valueOf(1));



        given(cardService.depositCard(Long.valueOf(1), 300.00)).willReturn(DepositResponseDTO.builder()
                .gpa_id(Long.valueOf(1)).balance(1300.00).build());

        mockMvc.perform(post("/card/deposit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isBadRequest());

    }

        static String asJsonString(final Object obj){
            try{
                return new ObjectMapper().writeValueAsString(obj);
            }catch (Exception e){
                throw new RuntimeException("error when wrap json");
            }
        }





    }
