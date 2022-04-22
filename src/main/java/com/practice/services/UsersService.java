package com.practice.services;

import com.practice.model.User;
import com.practice.repository.UserRepository;

import java.util.ArrayList;

public class UsersService {
    private  final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean getJoinUserResponse(String userName) {
        return !userRepository.exists(userName);
    }

    public boolean getLoginResponse(String userId, String userName){
        User user = new User(userId, userName);
        return userRepository.existsUser(user);
    }
    public ArrayList<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public String addUser(String userName){
        return userRepository.addUser(userName);
    }
}