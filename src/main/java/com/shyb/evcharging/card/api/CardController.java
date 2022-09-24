package com.shyb.evcharging.card.api;

import com.shyb.evcharging.card.application.CardService;
import com.shyb.evcharging.card.dto.CardRequestDto;
import com.shyb.evcharging.card.dto.CardResponseDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 생성, 조회, 수정, 삭제에 대한 요청 및 응답을 처리합니다.
 */

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final CardService cardService;

    /**
     * 카드를 등록한 후 리턴합니다.
     *
     * @param card 생성 요청 카드 정보
     * @return 등록된 카드
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CardResponseDto save(@RequestBody @Valid CardRequestDto card) {
        return cardService.save(card);
    }

}
