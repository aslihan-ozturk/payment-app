package com.example.paymentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="merchant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long merchantId;
    @NotBlank(message = "mcc required")
    private String mcc;
    @NotBlank(message = "merchant name required")
    private String merchantName;
    @NotNull(message = "gpaId required")
    private Long gpaId;
}

