package com.example.card.serviceImpl;

import com.example.card.constants.CardsConstants;
import com.example.card.dto.CardDto;
import com.example.card.entity.Cards;
import com.example.card.exception.CardAlreadyExistsException;
import com.example.card.exception.ResourceNotFoundException;
import com.example.card.mapper.CardsMapper;
import com.example.card.repository.CardsRepository;
import com.example.card.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServcieImpl implements CardService {


    CardsRepository cardrepo;


    @Override
    public void createCard(String mobileNumber) {


        Optional<Cards> card =cardrepo.findByMobileNumber(mobileNumber);

        if(card.isPresent())
        {
            throw  new CardAlreadyExistsException("card already register with given Mobile numebr"+mobileNumber);
        }
        cardrepo.save(createNewCard(mobileNumber));

    }

    @Override
    public CardDto getByCardMobileNumber(String mobileNumber) {

        Cards cards = cardrepo.findByMobileNumber(mobileNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Card",
                                "mobileNumber",
                                mobileNumber
                        )
                );


        return CardsMapper.mapToCardsDto(cards,new CardDto());
    }


    public Cards createNewCard(String mobilenumber)
    {
        Cards card=new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);

        card.setCardNumber(String.valueOf(randomCardNumber));
        card.setMobileNumber(mobilenumber);
        card.setCardType(CardsConstants.CREDIT_CARD);
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return card;



    }


    public boolean updateCard(CardDto cardDto)
    {
        Cards cards=cardrepo.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Card",
                        "cardNumber",
                        cardDto.getCardNumber())
        );

        cardrepo.save(CardsMapper.mapToCards(cardDto,cards));

        return  true;

    }


    public boolean cardDeleted(String mobilenumber)
    {
        Cards c=cardrepo.findByMobileNumber(mobilenumber).orElseThrow(

                ()-> new ResourceNotFoundException("Card","MobileNumber",mobilenumber)
        );

        cardrepo.deleteById(c.getCardId());
        return true;
    }


}
