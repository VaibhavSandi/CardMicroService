package com.example.card.controller;

import com.example.card.constants.CardsConstants;
import com.example.card.dto.CardDto;
import com.example.card.dto.ErrorResponseDto;
import com.example.card.dto.ResponseDto;
import com.example.card.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/cards",produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
        name = "REST APIS for Cards",
        description = "CRUD REST APIs in Card"
)
@Validated
public class CardController {

    private CardService cardService;



    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create a new Card inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Card created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"

            )
    })

@PostMapping("/createcard")
    public ResponseEntity<ResponseDto> crateCrad(@Validated @RequestParam
                                                 @Pattern(regexp="(^$|[0-9]{10})",message = "mobile no must be 10 digits")
                                                 String mobileNumber)
    {
        cardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201 ));
    }
@Operation(
        summary = "REST API for Fetch Card Details Against Mobile number",
        description = "Get Card by mobile number"
)
@ApiResponses({

             @ApiResponse(

                     responseCode =  "200",
                     description = "OK"

             ),

        @ApiResponse(
                responseCode = "404",
                description = "NOT_FOUND"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "INTERNAL_SERVER_ERROR"
        )


})

    @GetMapping("/getcardbyNumber")
    public ResponseEntity<CardDto> getcardByMobileNumber(@Validated @RequestParam
                                                                 @Pattern(regexp="(^$|[0-9]{10})",message = "mobile no must be 10 digits")
                                                                 String mobileNumber)
    {

       CardDto card =cardService.getByCardMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

    @Operation(
            summary = "REST API for Update Card Details",
            description = "For updating Card Details"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description = "Update operation failed"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_SERVER_ERROR"
                    )
            }

    )
    @PutMapping("/update")
public ResponseEntity<ResponseDto> updateCreditCard(@Valid @RequestBody CardDto card)
    {
        boolean isUpdated=cardService.updateCard(card);
        if(isUpdated)
        {
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE));
        }
    }



    @Operation(
            summary = "REST API for Delete Card Details",
            description = "REST API for delete Card details"

    )
    @ApiResponses({

            @ApiResponse(

                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed"

            ),
            @ApiResponse(

                   responseCode = "500",
                    description = "Internal_Server_Error"
            )




    })


@DeleteMapping("/deleteCard")
    public ResponseEntity<ResponseDto> deleteCard(@Validated @RequestParam
                              @Pattern(regexp="(^$|[0-9]{10})",message = "mobile no must be 10 digits")
                              String mobileNumber)
    {
        boolean isDelete=cardService.cardDeleted(mobileNumber);

        if(isDelete)
        {
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE));
        }
    }


}
