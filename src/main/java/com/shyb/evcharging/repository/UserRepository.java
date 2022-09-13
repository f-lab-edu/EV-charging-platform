package com.shyb.evcharging.repository;

import com.shyb.evcharging.repository.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll(UserSearchCond cond);
}
