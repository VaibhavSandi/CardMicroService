package com.example.card.repository;

import com.example.card.entity.Cards;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Long> {


   Optional<Cards> findByMobileNumber(String mobilenumber);

    Optional<Cards> findByCardNumber( String cardNumber);
}
