package com.shyb.evcharging.user.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchCond {

    private String name;

    private String password;

    private String phone;

    private String email;

    public UserSearchCond() {

    }

    public UserSearchCond(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
