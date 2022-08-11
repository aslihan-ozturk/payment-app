package com.example.paymentapp.model.requestDTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepositRequestDTO {
    @NotNull
    private Long cardId;
    @NotNull
    private Double amount;
}
