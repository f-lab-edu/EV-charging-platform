package com.shyb.evcharging.user.application;

import static com.shyb.evcharging.utils.UserFixture.*;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import com.shyb.evcharging.user.exception.EmailDuplicateException;
import com.shyb.evcharging.user.exception.PasswordMisMatchException;
import com.shyb.evcharging.user.exception.UserNotFoundException;
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

    UserResponseDto userResponseDto;
    UserRequestDto userRequestDTO;
    UserRequestDto userRequestDTOWithPasswordMismatch;

    @BeforeEach
    void setUp() {
        userResponseDto = RESPONSE;
        userRequestDTO = VALID_REQUEST;
        userRequestDTOWithPasswordMismatch = INVALID_WITH_DIFFERENT_PASSWORDS_REQUEST;
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
                    return User.builder()
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

        @Nested
        @DisplayName("패스워드와 패스워드 확인에 입력값이 다른 경우")
        class Context_save_user_with_invalid_passwords {

            @DisplayName("PasswordMisMatchException 예외를 던진다.")
            @Test
            void save_user_with_invalid_passwords() {
                // when, then
                assertThatThrownBy(() -> userService.save(userRequestDTOWithPasswordMismatch))
                    .isInstanceOf(PasswordMisMatchException.class);
            }
        }
    }

    @Nested
    @DisplayName("checkEmailDuplicate 메소드는")
    class Context_checkEmailDuplicate_method {

        @Test
        @DisplayName("이메일 중복이 없는 경우 어떤 예외도 던지지 않는다.")
        void checkEmailDuplicateWithNoDuplicates() {
            // given
            given(userRepository.findByEmail(NOT_EXISTING_EMAIL)).willReturn(Optional.empty());

            // when, then
            assertThatCode(() -> userService.checkEmailDuplicate(NOT_EXISTING_EMAIL))
                .doesNotThrowAnyException();

            verify(userRepository).findByEmail(NOT_EXISTING_EMAIL);
        }

        @Test
        @DisplayName("이메일 중복이 있는 경우, 예외를 던진다.")
        void checkEmailDuplicateWitDuplicates() {
            //given
            User user = User.builder()
                .id(1L)
                .name("tempName")
                .email("tempEmail")
                .password("tempPassword")
                .build();

            given(userRepository.findByEmail(EXISTING_EMAIL)).willReturn(Optional.of(user));

            // when, then
            assertThatThrownBy(() -> userService.checkEmailDuplicate(EXISTING_EMAIL))
                .isInstanceOf(EmailDuplicateException.class);

            verify(userRepository).findByEmail(EXISTING_EMAIL);

        }
    }

    @Nested
    @DisplayName("update 메소드는")
    class Context_update_user {

        UserModifyRequestDto userModifyRequestDto;
        User existingUser;
        User updatedUser;

        @BeforeEach
        void setUp() {
            userModifyRequestDto = UPDATE_REQUEST;
            existingUser = EXISTING_USER;
            updatedUser = UPDATED_USER;
        }

        @DisplayName("사용자가 있으면 사용자 정보를 수정한 후 리턴한다.")
        @Test
        void update_with_valid() {
            // given
            given(userRepository.findById(USER_ID))
                .willReturn(Optional.of(existingUser))
                .willReturn(Optional.of(updatedUser));

            // when
            UserResponseDto updatedUser = userService.update(USER_ID, userModifyRequestDto);

            // then
            assertThat(updatedUser.getName()).isEqualTo(UPDATE_NAME);
            assertThat(updatedUser.getPhone()).isEqualTo(UPDATE_PHONE);
        }

        @DisplayName("등록을 요청하는 사용자가 없는 경우 UserNotFoundException 예외를 던진다.")
        @Test
        void update_with_not_found_user() {
            // given
            given(userRepository.findById(USER_ID_NOT_STORED))
                .willReturn(Optional.empty());

            assertThatThrownBy(() -> userService.update(USER_ID_NOT_STORED, userModifyRequestDto))
                .isInstanceOf(UserNotFoundException.class);

            verify(userRepository).findById(USER_ID_NOT_STORED);
        }

        @DisplayName("변경 패스워드와 패스워드 확인이 모두 null이 아니며 동일하지 않은 경우 PasswordMismatch 예외를 던진다.")
        @Test
        void update_with_different_passwords() {
            assertThatThrownBy(() -> userService.update(USER_ID, UPDATE_WITH_DIFFERENT_PASSWORDS_REQUEST))
                .isInstanceOf(PasswordMisMatchException.class);
        }
    }
}
