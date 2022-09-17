package com.shyb.evcharging.user.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.EmailDuplicateCheckRequestDto;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import com.shyb.evcharging.user.exception.EmailDuplicateException;
import com.shyb.evcharging.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@DisplayName("UserService의 ")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    String VALID_NAME = "홍길동";

    String VALID_PHONE = "010-1234-5678";

    String VALID_EMAIL = "abcd@gmail.com";
    String EXISTING_EMAIL = "existing@gmail.com";
    String NOT_EXISTING_EMAIL = "non_exsting@gmail.com";

    String PASSWORD = "12345";
    String CONFIRM_PASSWORD = "12345";
    String CONFIRM_PASSWORD_WITH_MISMATCH = "55555";

    UserResponseDto userResponseDto;
    UserRequestDto userRequestDTO;

    @BeforeEach
    void setUp() {
        userResponseDto = UserResponseDto.builder().id(1L)
            .name(VALID_NAME)
            .phone(VALID_PHONE)
            .email(VALID_EMAIL)
            .build();

        userRequestDTO = UserRequestDto.builder()
            .name(VALID_NAME)
            .phone(VALID_PHONE)
            .email(VALID_EMAIL)
            .password(PASSWORD)
            .confirmPassword(CONFIRM_PASSWORD)
            .build();
    }

    @Nested
    @DisplayName("save 메소드는")
    class Context_save_method {

        @Nested
        @DisplayName("정상적으로 요청이 온 경우")
        class Context_save_valid_user {

            @DisplayName("사용자를 등록 후 리턴한다.")
            @Test
            void saveUser() {
                // given
                given(userRepository.save(any(User.class))).will(invocation -> {
                    User source = invocation.getArgument(0);
                    return UserResponseDto.builder()
                        .id(1L)
                        .name(source.getName())
                        .phone(source.getPhone())
                        .email(source.getEmail())
                        .build();
                });

                // when
                UserResponseDto user = userService.save(userRequestDTO);

                // then
                assertThat(user.getName()).isEqualTo(userRequestDTO.getName());
                assertThat(user.getPhone()).isEqualTo(userRequestDTO.getPhone());
                assertThat(user.getEmail()).isEqualTo(userRequestDTO.getEmail());

                verify(userRepository).save(any(User.class));
            }
        }
    }

    @Nested
    @DisplayName("checkEmailDuplicate 메소드는")
    class Context_checkEmailDuplicate_method {

        @Test
        @DisplayName("이메일 중복이 없는 경우 true를 리턴한다.")
        void checkEmailDuplicateWithNoDuplicates() {
            // given
            given(userRepository.findByEmail(NOT_EXISTING_EMAIL)).willReturn(Optional.empty());

            // when
            boolean emailExist = userService.checkEmailDuplicate(new EmailDuplicateCheckRequestDto(NOT_EXISTING_EMAIL));

            // then
            assertThat(emailExist).isTrue();
            verify(userRepository).findByEmail(NOT_EXISTING_EMAIL);
        }

        @Test
        @DisplayName("이메일 중복이 있는 경우, 예외를 던진다.")
        void checkEmailDuplicateWitDuplicates() {
            //given
            given(userRepository.findByEmail(EXISTING_EMAIL)).willReturn(Optional.of(userResponseDto));

            // when, then
            assertThatThrownBy(() -> userService.checkEmailDuplicate(new EmailDuplicateCheckRequestDto(EXISTING_EMAIL)))
                .isInstanceOf(EmailDuplicateException.class);

            verify(userRepository).findByEmail(EXISTING_EMAIL);

        }
    }
}
