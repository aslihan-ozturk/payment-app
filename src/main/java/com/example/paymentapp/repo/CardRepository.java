package com.example.paymentapp.repo;

import com.example.paymentapp.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long > {

    List<Card> getCardsByGpaId(Long gpaId);
}
