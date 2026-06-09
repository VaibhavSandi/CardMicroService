package com.example.card.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CARD_DETAILS")
public class Cards extends BaseEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "CARD_ID")
    private Long cardId;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
    @Column(name = "CARD_NUMBER")
    private String cardNumber;
    @Column(name = "CARD_TYPE")
    private String cardType;
    @Column(name = "TOTAL_LIMIT")
    private int totalLimit;
    @Column(name = "AMOUNT_USED")
    private int amountUsed;
    @Column(name = "AVAILABLE_AMOUNT")
    private int availableAmount;


}
