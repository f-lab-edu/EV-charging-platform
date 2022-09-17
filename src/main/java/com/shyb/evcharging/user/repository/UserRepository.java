package com.shyb.evcharging.user.repository;


import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserResponseDto;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserResponseDto save(User user);
    Optional<UserResponseDto> findById(Long id);
    List<UserResponseDto> findAll(UserSearchCond cond);
    Optional<UserResponseDto> findByEmail(String email);
}
