package com.shyb.evcharging.service;

import com.shyb.evcharging.repository.UserSearchCond;
import com.shyb.evcharging.repository.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll(UserSearchCond userSearch);
}
