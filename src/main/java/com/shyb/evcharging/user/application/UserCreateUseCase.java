package com.shyb.evcharging.user.application;

import com.shyb.evcharging.user.dto.UserCreateRequest;
import com.shyb.evcharging.user.dto.UserResponse;

public interface UserCreateUseCase {
    public UserResponse create(UserCreateRequest request);
}
