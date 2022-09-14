package com.shyb.evcharging.user.dto;

import com.shyb.evcharging.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserCreateRequest {
    private String name;
    private String email;

    @Builder
    public UserCreateRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
            .name(this.getName())
            .email(this.getEmail())
            .build();
    }
}
