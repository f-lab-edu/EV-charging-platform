package com.shyb.evcharging.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;

    @Builder
    public UserResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
