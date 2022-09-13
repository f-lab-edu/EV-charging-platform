package com.shyb.evcharging.repository.mybatis;

import com.shyb.evcharging.repository.UserRepository;
import com.shyb.evcharging.repository.UserSearchCond;
import com.shyb.evcharging.repository.domain.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisUserRepository implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        userMapper.save(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAll(UserSearchCond cond) {
        return userMapper.findAll(cond);
    }
}
