package com.example.paymentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @NotNull(message = "gpaId required")
    private Long gpaId;
    @NotBlank(message = "cardNumber required")
    private String cardNumber;
    @NotBlank(message = "cvv required")
    private String cvv;
    @NotBlank(message = "expiry required")
    private String expiry;
    @NotBlank(message = "card state required")
    private String state;
    @NotBlank(message = "mccList required")
    private String mccList;




}

