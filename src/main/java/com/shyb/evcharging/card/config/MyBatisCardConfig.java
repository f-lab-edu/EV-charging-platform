package com.shyb.evcharging.card.config;

import com.shyb.evcharging.card.repository.CardRepository;
import com.shyb.evcharging.card.repository.mybatis.CardMapper;
import com.shyb.evcharging.card.repository.mybatis.MyBatisCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class MyBatisCardConfig {

    private final CardMapper cardMapper;

    /**
     * MyBatis Repository를 Bean으로 등록합니다.
     *
     * @return
     */
    @Bean
    public CardRepository cardRepository() {
        return new MyBatisCardRepository(cardMapper);
    }
}
