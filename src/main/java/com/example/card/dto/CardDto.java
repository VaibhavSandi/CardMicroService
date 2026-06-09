package com.example.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
@Schema(
        name = "Cards",
        description = "Schema to hold Card information "
)
@Data
public class CardDto {


    @NotEmpty(message = "Mobile Number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @Schema(
            description = "Mobile number of Customer",example = "123456789"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card Number can not be empty or null")
    @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
    @Schema(
            description = "Card number of the customer",example = "123123123123"
    )
    private String cardNumber;
@NotEmpty(message = "Card Type Can not be Empty")
@Schema(
        description = "Type of the card " ,example = "Credit Card"
)
private String cardType;
    @Positive(message = "Total Card Limit should be greater than zero")
    @Schema(description = "Total amount limit available against Card ",example = "10000")

    private int totalLimit;

    @PositiveOrZero(message = "Total Amount use should be equal or greater than zero")
    @Schema(description = "Total amount used by customer ",example = "10000")

    private int amountUsed;
    @PositiveOrZero(message = "Total available Amount  should be equal or greater than zero")
    @Schema(description = "Total available amount against a card ",example = "10000")
    private int availableamount;


}
