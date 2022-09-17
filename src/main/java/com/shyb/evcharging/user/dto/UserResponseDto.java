package com.shyb.evcharging.user.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * 사용자 응답 DTO 입니다.
 */
@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String name;

    private String phone;

    private String email;

    public UserResponseDto(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
