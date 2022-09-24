package com.shyb.evcharging.card.repository.mybatis;

import com.shyb.evcharging.card.domain.Card;
import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CardMapper {

    void save(Card card);

    Optional<Card> findById(Long id);

}
