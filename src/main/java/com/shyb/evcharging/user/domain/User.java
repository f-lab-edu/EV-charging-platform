package com.shyb.evcharging.user.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User{

    private Long id;

    private String name;

    private String password;

    private String phone;

    private String email;

    public User() {

    }

    @Builder
    public User(String name, String password, String phone, String email) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
