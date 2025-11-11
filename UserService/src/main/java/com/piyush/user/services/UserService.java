package com.piyush.user.services;

import com.piyush.user.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    User updateUser(User user);

}
