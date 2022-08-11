package com.example.paymentapp.model.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositResponseDTO {
    private Long gpa_id;
    private Double balance;
}
