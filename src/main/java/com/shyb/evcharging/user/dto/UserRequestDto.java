package com.shyb.evcharging.user.dto;

import com.shyb.evcharging.user.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 등록 요청 DTO 입니다.
 */
@Data
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message="이름을 입력해주세요.")
    @Size(max=20, message="이름의 최대 길이는 20자 입니다.")
    @Pattern(regexp = "^[가-힇]+$", message = "이름은 한글로만 이루어져야 합니다.")
    private String name;

    @NotNull(message = "패스워드를 입력해주세요.")
    @NotBlank(message="패스워드를 입력해주세요.")
    private String password;

    @NotNull(message = "패스워드 확인란을 입력해주세요.")
    @NotBlank(message="패스워드를 입력해주세요.")
    private String confirmPassword;

    @Pattern(regexp = "010-[0-9]{4}-[0-9]{4}", message = "010-XXXX-XXXX 형식으로 입력해주세요.")
    private String phone;

    @NotBlank(message="메일을 입력해주세요.")
    @Email(message = "이메일 형식이 잘못되었습니다.")
    private String email;

    @Builder
    public UserRequestDto(String name, String password, String confirmPassword, String phone, String email) {
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.email = email;
    }

    public User toEntity(String encryptedPassword) {
        return User.builder()
            .name(this.getName())
            .password(encryptedPassword)
            .phone(this.getPhone())
            .email(this.getEmail())
            .build();
    }
}
