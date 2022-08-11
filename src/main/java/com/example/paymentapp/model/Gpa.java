package com.example.paymentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="gpa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gpaId;
    @NotNull(message = "amount required")
    private Double balance;

}
