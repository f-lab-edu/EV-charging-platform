package com.shyb.evcharging.service;

import com.shyb.evcharging.repository.UserRepository;
import com.shyb.evcharging.repository.UserSearchCond;
import com.shyb.evcharging.repository.domain.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll(UserSearchCond userSearch) {
        return userRepository.findAll(userSearch);
    }
}
