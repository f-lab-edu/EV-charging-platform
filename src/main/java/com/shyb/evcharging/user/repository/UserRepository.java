package com.shyb.evcharging.user.repository;


import com.shyb.evcharging.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll(UserSearchCond cond);
}
