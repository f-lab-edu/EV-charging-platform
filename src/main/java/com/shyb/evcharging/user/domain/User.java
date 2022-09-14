package com.shyb.evcharging.user.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
    private String email;

    @Builder
    public User(Long id, String name, String email) {

        if (name != null || StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("잘못된 값, 이름은 공백이나 널이 올 수 없음");
        }

        this.id = id;
        this.name = name;
        this.email = email;
    }
}
