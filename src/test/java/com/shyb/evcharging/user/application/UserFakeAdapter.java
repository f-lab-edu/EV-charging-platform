package com.shyb.evcharging.user.application;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserFakeAdapter implements UserRepository {
    private List<User> users = new ArrayList<User>();

    @Override
    public User save(User user) {
        users.add(user);

        return user;
    }
}
