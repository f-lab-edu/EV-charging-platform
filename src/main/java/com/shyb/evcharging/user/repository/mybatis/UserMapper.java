package com.shyb.evcharging.repository.mybatis;

import com.shyb.evcharging.repository.UserSearchCond;
import com.shyb.evcharging.repository.domain.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void save(User user);

    List<User> findAll(UserSearchCond userSearch);

    Optional<User> findById(Long id);

}
