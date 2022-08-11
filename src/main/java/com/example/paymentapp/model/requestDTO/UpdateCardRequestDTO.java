package com.example.paymentapp.model.requestDTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCardRequestDTO {

    @NotNull
    private Long cardId;

    @NotBlank
    private String cvv;

    @NotBlank
    private String expiry;
}
