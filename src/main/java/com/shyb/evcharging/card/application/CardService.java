package com.shyb.evcharging.card.application;

import com.shyb.evcharging.card.domain.Card;
import com.shyb.evcharging.card.dto.CardRequestDto;
import com.shyb.evcharging.card.dto.CardResponseDto;
import com.shyb.evcharging.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    /**
     * 카드를 등록한 후 리턴합니다.
     *
     * @param card 생성 요청 카드 정보
     * @eturn 등록된 카드
     */
    public CardResponseDto save(CardRequestDto card) {

        Card savedCard = cardRepository.save(card.toEntity());

        return CardResponseDto.builder()
            .id(savedCard.getId())
            .cardNumber(savedCard.getCardNumber())
            .cvc(savedCard.getCvc())
            .expiryYear(savedCard.getExpiryYear())
            .expiryMonth(savedCard.getExpiryMonth())
            .build();
    }

}
