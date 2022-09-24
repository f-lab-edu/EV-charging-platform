package com.shyb.evcharging.utils;

import com.shyb.evcharging.card.dto.CardRequestDto;

public class CardFixture {

    public static String VALID_CARD_NUMBER = "1234-1234-1234-1234";

    public static String VALID_CVC = "123";

    public static String VALID_EXPIRY_YEAR = "09";

    public static String VALID_EXPIRY_MONTH = "24";

    public static final CardRequestDto VALID_REQUEST = CardRequestDto
        .builder()
        .cardNumber(VALID_CARD_NUMBER)
        .cvc(VALID_CVC)
        .expiryYear(VALID_EXPIRY_YEAR)
        .expiryMonth(VALID_EXPIRY_MONTH)
        .build();

}
