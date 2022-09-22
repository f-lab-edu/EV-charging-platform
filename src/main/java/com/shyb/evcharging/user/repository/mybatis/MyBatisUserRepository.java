package com.shyb.evcharging.user.repository.mybatis;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import com.shyb.evcharging.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * MyBatis-UserRepository 입니다.
 */
@Repository
@RequiredArgsConstructor
public class MyBatisUserRepository implements UserRepository {

    private final UserMapper userMapper;

    /**
     * 사용자를 등록한 후 리턴합니다.
     *
     * @param user 생성 요청 사용자 정보
     * @return 등록된 사용자
     */
    @Override
    public User save(User user) {
        userMapper.save(user);
        return user;
    }

    /**
     * 사용자를 조회합니다.
     *
     * @param id 조회할 사용자 식별자
     * @return 조회된 사용자
     */
    @Override
    public Optional<User> findById(Long id) {
        return userMapper.findById(id);
    }

    /**
     * 이메일을 통해 가입된 사용자를 조회합니다.
     *
     * @param email 조회할 사용자 이메일
     * @return 조회된 사용자
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }


    @Override
    public Long update(Long id, User updateUser) {
        return userMapper.update(id, updateUser);
    }
}
