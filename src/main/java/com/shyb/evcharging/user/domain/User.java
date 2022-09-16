package com.shyb.evcharging.user.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 객체입니다.
 */
@Getter
@Setter
public class User{

    /**
     * 사용자 id
     */
    private Long id;

    /**
     * 사용자 이름
     */
    private String name;

    /**
     * 사용자 패스워드
     */
    private String password;

    /**
     * 사용자 휴대폰 번호
     */
    private String phone;

    /**
     * 사용자 이메일
     */
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
