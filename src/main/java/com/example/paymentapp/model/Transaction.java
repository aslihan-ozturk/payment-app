package com.example.paymentapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @NotNull(message = "cardId required")
    private Long cardId;
    @NotNull(message = "date required")
    private LocalDateTime transactionDate;
    @NotNull(message = "amount required")
    private Double transactionAmount;
    @NotNull(message = "merchant required")
    private Long merchantId;


}