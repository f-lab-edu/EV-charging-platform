package com.shyb.evcharging.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 예외가 던져졌을 때 사용하는 응답 형식 입니다.
 */
@Getter
@Setter
public class ErrorResponse {

    /**
     * 예외 메시지
     */
    private String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
