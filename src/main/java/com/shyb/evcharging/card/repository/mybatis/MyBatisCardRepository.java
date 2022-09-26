package com.shyb.evcharging.card.repository.mybatis;

import com.shyb.evcharging.card.domain.Card;
import com.shyb.evcharging.card.repository.CardRepository;
import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import com.shyb.evcharging.user.repository.UserRepository;
import com.shyb.evcharging.user.repository.mybatis.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * MyBatis-UserRepository 입니다.
 */
@Repository
@RequiredArgsConstructor
public class MyBatisCardRepository implements CardRepository {

    private final CardMapper cardMapper;

    /**
     * 카드를 등록한 후 리턴합니다.
     *
     * @param card 등록 요청 카드 정보
     * @return 등록된 카드
     */
    @Override
    public Card save(Card card) {
        cardMapper.save(card);
        return card;
    }

    /**
     * 카드를 조회합니다.
     *
     * @param id 조회할 카드 식별자
     * @return 조회된 카드
     */
    @Override
    public Optional<Card> findById(Long id) {
        return cardMapper.findById(id);
    }

    /**
     * 카드를 삭제합니다.
     * @param id 삭제할 카드 식별자
     */
    @Override
    public void delete(Long id) {
        cardMapper.delete(id);
    }

}
