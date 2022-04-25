package com.practice.repository;

import com.practice.model.User;

import java.util.ArrayList;

public interface UserRepository {
    User getUser(String userId);

    boolean exists(String userName);

    ArrayList<User> getAllUsers();

    String addUser(String userName);

    boolean existsUser(User user);

    ArrayList<User> getAllUsersExcept(String myId);

    boolean UpdateUser(String userId, String newUserName);

    boolean DeleteUser(String userId);
}
