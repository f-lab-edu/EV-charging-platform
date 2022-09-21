package com.shyb.evcharging.user.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shyb.evcharging.user.application.UserService;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import com.shyb.evcharging.util.MockMvcUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.shyb.evcharging.util.MockMvcUtil.OBJECT_MAPPER;
import static com.shyb.evcharging.util.UserFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerMockMvcTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcUtil.createMockMvc(userController);
    }

    @Test
    @DisplayName("유저 생성 가입 API - 성공")
    public void test() throws Exception {
        // given
        UserRequestDto request = REQUEST;
        UserResponseDto response = RESPONSE;

        given(userService.save(request)).willReturn(response);

        // when
        ResultActions result = mockMvc.perform(
            post("/api/v1/users")
                .content(OBJECT_MAPPER.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.name").value("테스트"));
    }

    @Test
    @DisplayName("유저 생성 가입 API - 실패")
    public void test2() throws Exception {
        // given
        UserRequestDto request = INVALID_NAME_REQUEST;

        // when
        ResultActions result = mockMvc.perform(
            post("/api/v1/users")
                .content(OBJECT_MAPPER.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errorMessage").exists());
    }
}
