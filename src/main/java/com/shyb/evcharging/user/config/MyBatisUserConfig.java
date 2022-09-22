package com.shyb.evcharging.user.config;

import com.shyb.evcharging.user.repository.UserRepository;
import com.shyb.evcharging.user.repository.mybatis.MyBatisUserRepository;
import com.shyb.evcharging.user.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class MyBatisUserConfig {
    private final UserMapper userMapper;

    /**
     * MyBatis Repository를 Bean으로 등록합니다.
     * @return
     */
    @Bean
    public UserRepository userRepository() {
        return new MyBatisUserRepository(userMapper);
    }
}
