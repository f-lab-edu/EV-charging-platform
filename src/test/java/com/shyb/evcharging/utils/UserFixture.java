package com.shyb.evcharging.utils;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;

public class UserFixture {
    public static Long USER_ID = 1L;
    public static Long USER_ID_NOT_STORED = 9999L;

    public static String VALID_NAME = "홍길동";

    public static String VALID_PHONE = "010-1234-5678";

    public static String VALID_EMAIL = "abcd@gmail.com";
    public static String EXISTING_EMAIL = "existing@gmail.com";
    public static String NOT_EXISTING_EMAIL = "non_exsting@gmail.com";

    public static String PASSWORD = "12345";
    public static String CONFIRM_PASSWORD = "12345";
    public static String CONFIRM_PASSWORD_WITH_MISMATCH = "55555";

    public static String UPDATE_NAME = "후후후";
    public static String UPDATE_PHONE = "010-1234-5678";
    public static String UPDATE_PASSWORD = "111";
    public static String UPDATE_CONFIRM_PASSWORD = "111";


    public static final UserRequestDto VALID_REQUEST = UserRequestDto
        .builder()
        .name(VALID_NAME)
        .email(VALID_EMAIL)
        .phone(VALID_PHONE)
        .password(PASSWORD)
        .confirmPassword(CONFIRM_PASSWORD)
        .build();

    public static final UserRequestDto INVALID_WITH_DIFFERENT_PASSWORDS_REQUEST = UserRequestDto
        .builder()
        .name(VALID_NAME)
        .email(VALID_EMAIL)
        .phone(VALID_PHONE)
        .password(PASSWORD)
        .confirmPassword(CONFIRM_PASSWORD_WITH_MISMATCH)
        .build();

    public static final UserRequestDto INVALID_NAME_REQUEST = UserRequestDto
        .builder()
        .name("")
        .email("test@naver.com")
        .phone("010-4453-8737")
        .password("12345678")
        .confirmPassword("12345678")
        .build();

    public static final UserResponseDto RESPONSE = UserResponseDto
        .builder()
        .id(USER_ID)
        .name(VALID_NAME)
        .email(VALID_EMAIL)
        .phone(VALID_PHONE)
        .build();

    public static final UserModifyRequestDto UPDATE_REQUEST = UserModifyRequestDto
        .builder()
        .name(UPDATE_NAME)
        .phone(UPDATE_PHONE)
        .password(UPDATE_PASSWORD)
        .confirmPassword(UPDATE_CONFIRM_PASSWORD)
        .build();

    public static final UserModifyRequestDto UPDATE_WITH_DIFFERENT_PASSWORDS_REQUEST = UserModifyRequestDto
        .builder()
        .name(UPDATE_NAME)
        .phone(UPDATE_PHONE)
        .password(UPDATE_PASSWORD)
        .confirmPassword(CONFIRM_PASSWORD_WITH_MISMATCH)
        .build();


    public static final User EXISTING_USER = User
        .builder()
        .id(USER_ID)
        .name(VALID_NAME)
        .email(VALID_EMAIL)
        .phone(VALID_PHONE)
        .build();

    public static final User UPDATED_USER = User
        .builder()
        .id(USER_ID)
        .name(UPDATE_NAME)
        .phone(UPDATE_PHONE)
        .email(VALID_EMAIL)
        .build();

}
