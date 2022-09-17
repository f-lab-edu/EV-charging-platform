package com.shyb.evcharging.user.dto;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.exception.PasswordMisMatchException;
import com.shyb.evcharging.utils.PasswordEncoder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/**
 * 사용자 등록 요청 DTO 입니다.
 */
@Getter
public class UserRequestDto {

    @NotBlank(message="이름을 입력해주세요.")
    @Size(max=20, message="이름의 최대 길이는 20자 입니다.")
    @Pattern(regexp = "^[가-힇]+$", message = "이름은 한글로만 이루어져야 합니다.")
    private String name;

    private String password;

    private String confirmPassword;

    @Pattern(regexp = "010-[0-9]{4}-[0-9]{4}", message = "010-XXXX-XXXX 형식으로 입력해주세요.")
    private String phone;

    @NotBlank(message="메일을 입력해주세요.")
    @Email(message = "이메일 형식이 잘못되었습니다.")
    private String email;

    @Builder
    public UserRequestDto(String name, String password, String confirmPassword, String phone, String email) {

        checkPasswordMatch(password, confirmPassword);

        this.name = name;
        this.password = PasswordEncoder.encrypt(password);
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
            .name(this.getName())
            .password(this.getPassword())
            .phone(this.getPhone())
            .email(this.getEmail())
            .build();
    }

    /**
     * 패스워드와 패스워드 확인에 입력된 값이 동일한지 확인합니다.
     * 만약 동일하지 않다면 예외를 던집니다.
     *
     * @param password 패스워드 란에 입력된 패스워드
     * @param confirmPassword 패스워드 확인란에 입력된 패스워드
     */
    private void checkPasswordMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordMisMatchException();
        }
    }
}
