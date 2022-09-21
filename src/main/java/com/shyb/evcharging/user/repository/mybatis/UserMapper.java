package com.shyb.evcharging.user.repository.mybatis;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Long update(Long id, @Param("updateUser") User updateUser);

}
