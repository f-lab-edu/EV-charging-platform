package com.shyb.evcharging.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDuplicateCheckRequestDto {

    @NotBlank(message="메일을 입력해주세요.")
    @Email(message = "이메일 형식이 잘못되었습니다.")
    private String email;

    public EmailDuplicateCheckRequestDto(String email) {
        this.email = email;
    }
}
