package com.shyb.evcharging.user.api;

import com.shyb.evcharging.user.application.UserService;
import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User save(@RequestBody UserRequestDto user) {
        return userService.save(user);
    }

    @GetMapping("/users/{id}")
    public String find(@PathVariable("id") long id) {
        if (userService.findById(id).isPresent()) {
            return "find user";
        } else {
            return "no user";
        }
    }

}
