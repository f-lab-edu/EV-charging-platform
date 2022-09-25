package com.shyb.evcharging.card.api;

import static com.shyb.evcharging.utils.CardFixture.CARD_ID;
import static com.shyb.evcharging.utils.CardFixture.INVALID_CARD_ID;
import static com.shyb.evcharging.utils.CardFixture.INVALID_MONTH_REQUEST;
import static com.shyb.evcharging.utils.CardFixture.VALID_CARD_NUMBER;
import static com.shyb.evcharging.utils.CardFixture.VALID_REQUEST;
import static com.shyb.evcharging.utils.CardFixture.VALID_RESPONSE;
import static com.shyb.evcharging.utils.MockMvcUtil.OBJECT_MAPPER;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.shyb.evcharging.card.application.CardService;
import com.shyb.evcharging.card.dto.CardRequestDto;
import com.shyb.evcharging.card.dto.CardResponseDto;
import com.shyb.evcharging.card.exception.CardExceptionHandler;
import com.shyb.evcharging.user.exception.EmailDuplicateException;
import com.shyb.evcharging.utils.MockMvcUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@DisplayName("CardController의 ")
@ExtendWith(MockitoExtension.class)
class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcUtil.createMockMvc(cardController, new CardExceptionHandler());
    }

    @DisplayName("카드 등록 API는")
    @Nested
    class Context_save_card {

        @DisplayName("카드 등록이 성공하면 상태코드 201과 등록된 카드를 리턴한다.")
        @Test
        void success_register_card() throws Exception {
            // given
            CardRequestDto request = VALID_REQUEST;
            CardResponseDto response = VALID_RESPONSE;

            given(cardService.save(request)).willReturn(response);

            //when
            ResultActions result = mockMvc.perform(
                post("/api/v1/cards")
                    .content(OBJECT_MAPPER.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            //then
            result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cardNumber").exists())
                .andExpect(jsonPath("$.cardNumber").value(VALID_CARD_NUMBER));
        }

        @DisplayName("입력값(유효기간, 월)이 올바르지 않으면 상태코드 400과 에러 메시지를 전달한다.")
        @Test
        void fail_register_card() throws Exception {
            ResultActions result = mockMvc.perform(
                post("/api/v1/cards")
                    .content(OBJECT_MAPPER.writeValueAsString(INVALID_MONTH_REQUEST))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }
    }

    @DisplayName("카드 삭제 API는")
    @Nested
    class Context_delete_card{

        @DisplayName("카드 삭제가 성공하면 상태코드 200을 리턴한다.")
        @Test
        void delete_card() throws Exception {

            ResultActions result = mockMvc.perform(
                delete("/api/v1/cards/" + CARD_ID)
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isOk());
        }

        @DisplayName("등록되지 않은 카드에 대한 삭제 요청이 오면 상태코드 400과 에러 메시지를  리턴한다.")
        @Test
        void delete_card_with_invalid_card() throws Exception {

            doThrow(new IllegalArgumentException("삭제 요청된 카드가 존재하지 않습니다.")).when(cardService).delete(INVALID_CARD_ID);

            ResultActions result = mockMvc.perform(
                delete("/api/v1/cards/" + INVALID_CARD_ID)
                    .contentType(MediaType.APPLICATION_JSON)
            );

            result
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").exists());
        }

    }
}
