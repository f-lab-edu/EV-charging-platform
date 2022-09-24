package com.shyb.evcharging.card.dto;

import com.shyb.evcharging.card.domain.Card;
import com.shyb.evcharging.card.dto.validator.ExpiryMonthConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardRequestDto {

    @NotBlank(message = "카드 번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}", message = "카드 번호를 올바른 형식(1111-1111-1111-1111)으로 입력해주세요.")
    private String cardNumber;

    @NotNull(message = "cvc번호를 입력해주세요.")
    @NotBlank(message = "cvc번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{3}", message = "cvc 번호가 올바르지 않습니다.")
    private String cvc;

    @NotNull(message = "유효기간을 입력해주세요.")
    @NotBlank(message = "유효기간을 입력해주세요.")
    @Pattern(regexp = "[0-9]{1}[0-9]{1}", message = "유효기간(년) 입력이 올바른지 확인해주세요.")
    private String expiryYear;

    @NotNull(message = "유효기간을 입력해주세요.")
    @NotBlank(message = "유효기간을 입력해주세요.")
    @ExpiryMonthConstraint
    private String expiryMonth;

    public Card toEntity() {
        return Card.builder()
            .cardNumber(this.cardNumber)
            .cvc(this.cvc)
            .expiryYear(this.expiryYear)
            .expiryMonth(this.expiryMonth)
            .build();
    }

    @Builder
    public CardRequestDto(String cardNumber, String cvc, String expiryYear, String expiryMonth) {
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
    }



}
