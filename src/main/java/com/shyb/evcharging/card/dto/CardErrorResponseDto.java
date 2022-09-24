package com.shyb.evcharging.card.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 예외가 던져졌을 때 사용하는 응답 형식 입니다.
 */
@Getter
@Setter
public class CardErrorResponseDto {

    /**
     * 예외 메시지
     */
    private String errorMessage;

    public CardErrorResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
