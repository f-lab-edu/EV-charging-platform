package com.shyb.evcharging.user.dto;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.utils.PasswordEncoder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRequestDto {

    private String name;

    private String password;

    private String confirmPassword;

    private String phone;

    private String email;

    @Builder
    public UserRequestDto(String name, String password, String confirmPassword, String phone, String email) {

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("패스워드를 다시 한 번 확인해주세요.");
        }

        this.name = name;
        this.password = PasswordEncoder.encrypt(password);
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
}
