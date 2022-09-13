package com.shyb.evcharging.controller;

import com.shyb.evcharging.repository.domain.User;
import com.shyb.evcharging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userServiceImpl;

    @PostMapping("/users")
    public User save(User user) {
        return userServiceImpl.save(user);
    }

    @GetMapping("/users/{id}")
    public String find(@PathVariable("id") long id) {
        if (userServiceImpl.findById(id).isPresent()) {
            return "find user";
        } else {
            return "no user";
        }
    }

}
