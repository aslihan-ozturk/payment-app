package com.example.paymentapp.model.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO implements Serializable {
    @NotNull
    private Long cardId;

    @NotNull
    private Double amount;

    @NotNull
    private Long merchantId;

}
