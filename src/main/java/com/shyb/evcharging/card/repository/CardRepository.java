package com.shyb.evcharging.card.repository;

import com.shyb.evcharging.card.domain.Card;
import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import java.util.Optional;

public interface CardRepository {
    Card save(Card user);
    Optional<Card> findById(Long id);
}
