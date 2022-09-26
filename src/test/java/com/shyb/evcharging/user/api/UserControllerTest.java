package com.shyb.evcharging.user.api;

import static com.shyb.evcharging.utils.MockMvcUtil.OBJECT_MAPPER;
import static com.shyb.evcharging.utils.UserFixture.*;

import com.shyb.evcharging.user.application.UserService;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import com.shyb.evcharging.user.exception.EmailDuplicateException;
import com.shyb.evcharging.user.exception.UserExceptionHandler;
import com.shyb.evcharging.user.exception.UserNotFoundException;
import com.shyb.evcharging.utils.MockMvcUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@DisplayName("UserController의 ")
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcUtil.createMockMvc(userController, new UserExceptionHandler());
    }

    @DisplayName("save 메소드는")
    @Nested
    class Context_save_method {

        @DisplayName("회원 가입이 성공하면 상태코드 201과 등록된 사용자를 리턴한다.")
        @Test
        void success_register_user() throws Exception {
            // given
            UserRequestDto request = VALID_REQUEST;
            UserResponseDto response = RESPONSE;

            given(userService.save(request)).willReturn(response);

            ResultActions result = mockMvc.perform(
                post("/api/v1/users")
                    .content(OBJECT_MAPPER.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value(VALID_NAME));

        }

        @DisplayName("입력된 이름이 적절하지 않으면 에러 메시지를 응답하고 상태코드 400를 리턴한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"!@#", "hello", "자바java", "자바!!", "스프링spring!!", "이름은이십자를초과할수없습니다를테스트합니다"})
        void name_request_with_invalid_name(String invalidName) throws Exception {
            // given
            UserRequestDto request = createRequestWithInvalidName(invalidName);

            ResultActions result = mockMvc.perform(
                post("/api/v1/users")
                    .content(OBJECT_MAPPER.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
//                .andExpect(content().string(containsString("이름을 입력해주세요.")));

        }

        @DisplayName("입력된 이메일이 적절하지 않으면 에러 메시지를 응답하고 상태코드 400를 리턴한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"email.com", "id@id@.com", "email"})
        void email_is_invalid(String invalidEmail) throws Exception {
            // given
            UserRequestDto request = createRequestWithInvalidEmail(invalidEmail);

            ResultActions result = mockMvc.perform(
                post("/api/v1/users")
                    .content(OBJECT_MAPPER.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }

        @DisplayName("입력된 전화번호 적절하지 않으면 에러 메시지를 응답하고 상태코드 400를 리턴한다.")
        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"01012341234", "010-123-1234", "010-@323-%@!!", "82-010-1234-1234"})
        void phone_is_invalid(String invalidPhone) throws Exception {
            // given
            UserRequestDto request = createRequestWithInvalidPhone(invalidPhone);

            ResultActions result = mockMvc.perform(
                post("/api/v1/users")
                    .content(OBJECT_MAPPER.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }

        @DisplayName("입력된 비밀번호는 null이거나 빈칸이라면 예외를 던지고 상태코드 400를 리턴한다.")
        @ParameterizedTest
        @NullAndEmptySource
        void password_is_not_null_or_not_blank(String invalidPassword) throws Exception {
            // given
            UserRequestDto request = createRequestWithInvalidPassword(invalidPassword);

            ResultActions result = mockMvc.perform(
                post("/api/v1/users")
                    .content(OBJECT_MAPPER.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }

        @DisplayName("입력된 비밀번호 확인란은 null이거나 빈칸이라면 예외를 던지고 상태코드 400를 리턴한다.")
        @ParameterizedTest
        @NullAndEmptySource
        void confirm_password_is_not_null_or_not_blank(String invalidConfirmPassword) throws Exception {
            // given
            UserRequestDto request = createRequestWithInvalidConfirmPassword(invalidConfirmPassword);

            ResultActions result = mockMvc.perform(
                post("/api/v1/users")
                    .content(OBJECT_MAPPER.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }
    }

    @DisplayName("checkEmailDuplicate 메소드는")
    @Nested
    class Context_checkEmailDuplicate_method {

        @DisplayName("이메일이 존재하면 EmailDuplicateException 예외를 던지고 상태 코드 400을 리턴한다.")
        @Test
        void email_is_duplicate() throws Exception {
            // given
            String existingEmail = EXISTING_EMAIL;

            doThrow(new EmailDuplicateException()).when(userService).checkEmailDuplicate(existingEmail);

            ResultActions result = mockMvc.perform(
                get("/api/v1/users/email/" + existingEmail + "/check")
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest());
        }

        @DisplayName("이메일이 존재하지 않으면 상태코드 200을 리턴한다.")
        @Test
//
        void email_is_not_duplicate() throws Exception {

            String notExistingEmail = NOT_EXISTING_EMAIL;

            doNothing().when(userService).checkEmailDuplicate(notExistingEmail);

            ResultActions result = mockMvc.perform(
                get("/api/v1/users/email/" + notExistingEmail + "/check")
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isOk());
        }

        @DisplayName("입력된 이메일의 형식이 맞지 않으면 예외를 던지고 상태 코드 400을 리턴한다.")
        @ParameterizedTest
        @ValueSource(strings = {"email.com", "id@id@.com", "email"})
        void email_format_is_invalid(String invalidEmail) throws Exception {
            // given
            doThrow(new EmailDuplicateException()).when(userService).checkEmailDuplicate(invalidEmail);

            ResultActions result = mockMvc.perform(
                get("/api/v1/users/email/" + invalidEmail + "/check")
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }
    }


    @DisplayName("update 메소드는")
    @Nested
    class Context_update_method {

        @DisplayName("사용자 정보 변경 요청이 오면 사용자를 변경하고 상태코드 200을 리턴한다.")
        @Test
        void update_user() throws Exception {
            UserModifyRequestDto updateRequest = UPDATE_REQUEST;

            given(userService.update(eq(USER_ID), any(UserModifyRequestDto.class)))
                .will(invocation -> {
                    Long id = invocation.getArgument(0);
                    UserModifyRequestDto user = invocation.getArgument(1);
                    return UserResponseDto.builder()
                        .id(id)
                        .name(user.getName())
                        .phone(user.getPhone())
                        .build();
                });

            ResultActions result = mockMvc.perform(
                patch("/api/v1/users/" + USER_ID)
                    .content(OBJECT_MAPPER.writeValueAsString(updateRequest))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value(updateRequest.getName()))
                .andExpect(jsonPath("$.phone").exists())
                .andExpect(jsonPath("$.phone").value(updateRequest.getPhone()));
        }

        @DisplayName("입력된 이름이 적절하지 않으면 에러 메시지와 상태코드 400를 리턴한다.")
        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"!@#", "hello", "자바java", "자바!!", "스프링spring!!", "이름은이십자를초과할수없습니다를테스트합니다"})
        void name_includes_invalid_character(String invalidName) throws Exception {
            // given
            UserModifyRequestDto updateRequest = updateRequestWithInvalidName(invalidName);

            ResultActions result = mockMvc.perform(
                patch("/api/v1/users/" + USER_ID)
                    .content(OBJECT_MAPPER.writeValueAsString(updateRequest))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());

        }

        @DisplayName("휴대폰 번호가 형식에 맞지 않게 전달되면 예외를 던지고 상태코드 400를 리턴한다.")
        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"01012341234", "010-123-1234", "010-@323-%@!!", "82-010-1234-1234"})
        void phone_is_invalid(String invalidPhone) throws Exception {
            // given
            UserModifyRequestDto updateRequest = updateRequestWithInvalidPhone(invalidPhone);

            ResultActions result = mockMvc.perform(
                patch("/api/v1/users/" + USER_ID)
                    .content(OBJECT_MAPPER.writeValueAsString(updateRequest))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }
    }

    @DisplayName("find 메소드는")
    @Nested
    class Context_find_method {

        @DisplayName("사용자가 존재하면 사용자 정보와 상태코드 200을 리턴한다.")
        @Test
        void user_exists() throws Exception {
            // given
            UserResponseDto response = RESPONSE;

            given(userService.findById(USER_ID)).willReturn(response);

            ResultActions result = mockMvc.perform(
                get("/api/v1/users/" + USER_ID)
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(USER_ID))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value(VALID_NAME))
                .andExpect(jsonPath("$.phone").exists())
                .andExpect(jsonPath("$.phone").value(VALID_PHONE))
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.email").value(VALID_EMAIL));

        }

        @DisplayName("사용자가 존재하지 않으면 예외를 던지고 상태코드 400을 리턴한다.")
        @Test
        void user_not_exists() throws Exception {
            // given
            doThrow(new UserNotFoundException()).when(userService).findById(USER_ID_NOT_STORED);

            ResultActions result = mockMvc.perform(
                get("/api/v1/users/" + USER_ID_NOT_STORED)
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }
    }

}

