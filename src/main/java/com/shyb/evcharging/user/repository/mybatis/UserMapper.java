package com.shyb.evcharging.user.repository.mybatis;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import com.shyb.evcharging.user.repository.UserSearchCond;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void save(User user);

    List<User> findAll(UserSearchCond userSearch);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Long update(Long id, @Param("userModifyRequestDto") UserModifyRequestDto userModifyRequestDto);

}
