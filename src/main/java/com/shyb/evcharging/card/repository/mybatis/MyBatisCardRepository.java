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
     * 사용자를 등록한 후 리턴합니다.
     *
     * @param user 생성 요청 사용자 정보
     * @return 등록된 사용자
     */
    @Override
    public Card save(Card user) {
        cardMapper.save(user);
        return user;
    }

    /**
     * 사용자를 조회합니다.
     *
     * @param id 조회할 사용자 식별자
     * @return 조회된 사용자
     */
    @Override
    public Optional<Card> findById(Long id) {
        return cardMapper.findById(id);
    }

}
