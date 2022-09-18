package com.shyb.evcharging.user.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 정보 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class UserModifyRequestDto{

    @Size(max=20, message="이름의 최대 길이는 20자 입니다.")
    @Pattern(regexp = "^[가-힇]+$", message = "이름은 한글로만 이루어져야 합니다.")
    private String name;

    private String password;

    private String confirmPassword;

    @Pattern(regexp = "010-[0-9]{4}-[0-9]{4}", message = "010-XXXX-XXXX 형식으로 입력해주세요.")
    private String phone;

}
