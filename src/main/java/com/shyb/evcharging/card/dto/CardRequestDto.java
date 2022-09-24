package com.shyb.evcharging.card.dto;

import com.shyb.evcharging.card.domain.Card;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardRequestDto {

    @NotBlank(message = "카드 번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}", message = "카드 번호를 올바른 형식(1111-1111-1111-1111)으로 입력해주세요.")
    private String cardNumber;

    @NotNull(message = "cvc번호를 입력해주세요.")
    @NotBlank(message = "cvc번호를 입력해주세요.")
    @Pattern(regexp = "[0-1]{3}", message = "cvc 번호가 올바르지 않습니다.")
    private String cvc;

    @NotNull(message = "유효기간을 입력해주세요.")
    @NotBlank(message = "유효기간을 입력해주세요.")
    @Pattern(regexp = "[0-1]{1}[0-9]{1}", message = "유효년이 올바르지 않습니다.")
    private String expiryYear;

    @NotNull(message = "유효기간을 입력해주세요.")
    @NotBlank(message = "유효기간을 입력해주세요.")
    private String expiryMonth;

    public Card toEntity() {
        return Card.builder()
            .cardNumber(this.cardNumber)
            .cvc(this.cvc)
            .expiryYear(this.expiryYear)
            .expiryMonth(this.expiryMonth)
            .build();
    }

}
