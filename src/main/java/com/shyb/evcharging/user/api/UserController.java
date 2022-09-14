package com.shyb.evcharging.user.api;

import com.shyb.evcharging.user.application.UserCreateService;
import com.shyb.evcharging.user.application.UserCreateUseCase;
import com.shyb.evcharging.user.dto.UserCreateRequest;
import com.shyb.evcharging.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserCreateUseCase userCreateUseCase;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest request) {
        UserResponse savedUser = userCreateUseCase.create(request);

        return ResponseEntity
            .created(URI.create("/api/v1/users/" + savedUser.getId()))
            .body(savedUser);
    }
}
