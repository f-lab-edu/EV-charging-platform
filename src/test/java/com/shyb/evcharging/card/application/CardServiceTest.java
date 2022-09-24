package com.shyb.evcharging.card.application;

import static com.shyb.evcharging.utils.CardFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.shyb.evcharging.card.domain.Card;
import com.shyb.evcharging.card.dto.CardResponseDto;
import com.shyb.evcharging.card.repository.CardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("CardService의 ")
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Nested
    @DisplayName("save 메소드는")
    class Context_save_method {

        @Nested
        @DisplayName("정상적으로 요청이 온 경우")
        class Context_save_valid {

            @DisplayName("카드를 등록 후 리턴한다.")
            @Test
            void saveCard() {
                // given
                given(cardRepository.save(any(Card.class))).will(invocation -> {
                    Card source = invocation.getArgument(0);
                    return Card.builder()
                        .id(1L)
                        .cardNumber(source.getCardNumber())
                        .cvc(source.getCvc())
                        .expiryYear(source.getExpiryYear())
                        .expiryMonth(source.getExpiryMonth())
                        .build();
                });

                CardResponseDto card = cardService.save(VALID_REQUEST);

                assertThat(card.getCardNumber()).isEqualTo(VALID_REQUEST.getCardNumber());
                assertThat(card.getCvc()).isEqualTo(VALID_REQUEST.getCvc());
                assertThat(card.getExpiryYear()).isEqualTo(VALID_REQUEST.getExpiryYear());
                assertThat(card.getExpiryMonth()).isEqualTo(VALID_REQUEST.getExpiryMonth());

            }
        }
    }

}
