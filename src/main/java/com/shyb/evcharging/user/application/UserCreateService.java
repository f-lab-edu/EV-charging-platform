package com.shyb.evcharging.user.application;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.domain.UserRepository;
import com.shyb.evcharging.user.dto.UserCreateRequest;
import com.shyb.evcharging.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCreateService implements UserCreateUseCase {
    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserCreateRequest request) {
        User savedUser = userRepository.save(request.toEntity());

        return UserResponse.builder()
            .id(savedUser.getId())
            .name(savedUser.getName())
            .email(savedUser.getEmail())
            .build();
    }
}

