package com.shyb.evcharging.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

public class UserFixture {
    public static final UserRequestDto REQUEST = UserRequestDto
        .builder()
        .name("테스트")
        .email("test@naver.com")
        .phone("010-4453-8737")
        .password("12345678")
        .confirmPassword("12345678")
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
        .name("테스트")
        .email("test@naver.com")
        .phone("010-4453-8737")
        .build();
}
