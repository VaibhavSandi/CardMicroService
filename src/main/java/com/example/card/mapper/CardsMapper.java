package com.example.card.mapper;

import com.example.card.dto.CardDto;
import com.example.card.entity.Cards;
import lombok.Data;

@Data
public class CardsMapper {

    public static CardDto mapToCardsDto(Cards cards, CardDto cardsDto) {
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableamount(cards.getAvailableAmount());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        return cardsDto;
    }

    public static Cards mapToCards(CardDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAvailableAmount(cardsDto.getAvailableamount());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        return cards;
    }

}
