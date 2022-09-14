package com.shyb.evcharging.user.application;

import com.shyb.evcharging.user.domain.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserCreateRequest;
import com.shyb.evcharging.user.dto.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


class UserCreateServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserCreateService userCreateService;


    @DisplayName("유저 생성 테스트")
    @Test
    public void test() {
        // given
        UserCreateRequest request = UserCreateRequest.builder().build();
        User savedUser = User.builder()
            .id(1L)
            .email("test@naver.com")
            .name("kyunam")
            .build();
        given(userRepository.save(any())).willReturn(savedUser);

        // when
        UserResponse response = userCreateService.create(request);

        // then
        assertThat(response.getEmail()).isEqualTo("test@naver.com");
        assertThat(response.getName()).isEqualTo("kyunam");
    }
}
