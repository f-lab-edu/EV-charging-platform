package com.shyb.evcharging.card.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 카드 객체입니다.
 */
@Getter
@Setter
@NoArgsConstructor
public class Card{

    /**
     * 카드 id
     */
    private Long id;

    /**
     * 카드 번호
     */
    private String cardNumber;

    /**
     * cvc 번호
     */
    private String cvc;

    /**
     * 카드 유효기간 - 년
     */
    private String expiryYear;

    /**
     * 카드 유효기간 - 월
     */
    private String expiryMonth;

    @Builder
    public Card(Long id, String cardNumber, String cvc, String expiryYear, String expiryMonth) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
    }
}
