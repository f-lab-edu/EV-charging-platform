package com.shyb.evcharging.config;

import com.shyb.evcharging.repository.UserRepository;
import com.shyb.evcharging.repository.mybatis.MyBatisUserRepository;
import com.shyb.evcharging.repository.mybatis.UserMapper;
import com.shyb.evcharging.service.UserService;
import com.shyb.evcharging.service.UserServiceImpl;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final UserMapper userMapper;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MyBatisUserRepository(userMapper);
    }

}
