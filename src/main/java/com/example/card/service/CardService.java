package com.example.card.service;

import com.example.card.dto.CardDto;
import jakarta.validation.constraints.Pattern;

public interface CardService {
    void createCard( String mobileNumber);

    CardDto getByCardMobileNumber(String mobileNumber);

    boolean updateCard(CardDto cardDto);

    boolean cardDeleted(String mobilenumber);
}
