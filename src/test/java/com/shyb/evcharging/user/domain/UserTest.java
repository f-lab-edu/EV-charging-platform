package com.shyb.evcharging.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    @DisplayName("유저 이름은 널이나 공백이 올 수 없음")
    @Test
    public void test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User(1L ,null, "aa");
        });
    }
}
