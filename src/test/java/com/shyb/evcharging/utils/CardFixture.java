package com.shyb.evcharging.utils;

import com.shyb.evcharging.card.dto.CardRequestDto;
import com.shyb.evcharging.card.dto.CardResponseDto;

public class CardFixture {

    public static Long CARD_ID = 1L;

    public static String VALID_CARD_NUMBER = "1234-1234-1234-1234";

    public static String VALID_CVC = "123";

    public static String VALID_EXPIRY_YEAR = "22";

    public static String VALID_EXPIRY_MONTH = "09";
    public static String INVALID_EXPIRY_MONTH = "24";

    public static final CardRequestDto VALID_REQUEST = CardRequestDto
        .builder()
        .cardNumber(VALID_CARD_NUMBER)
        .cvc(VALID_CVC)
        .expiryYear(VALID_EXPIRY_YEAR)
        .expiryMonth(VALID_EXPIRY_MONTH)
        .build();

    public static final CardRequestDto INVALID_MONTH_REQUEST = CardRequestDto
        .builder()
        .cardNumber(VALID_CARD_NUMBER)
        .cvc(VALID_CVC)
        .expiryYear(VALID_EXPIRY_YEAR)
        .expiryMonth(INVALID_EXPIRY_MONTH)
        .build();

    public static final CardResponseDto VALID_RESPONSE = CardResponseDto
        .builder()
        .id(CARD_ID)
        .cardNumber(VALID_CARD_NUMBER)
        .cvc(VALID_CVC)
        .expiryYear(VALID_EXPIRY_YEAR)
        .expiryMonth(VALID_EXPIRY_MONTH)
        .build();

}
