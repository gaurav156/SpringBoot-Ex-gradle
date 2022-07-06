package com.springboot.configdemo.MyApplication.fullstack.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    public List<User> getUserList() throws IOException {
        return usersRepo.getUserList();
    }

    public List<User> userFilter(String id) throws IOException {
        return usersRepo.userFilter(id);
    }

    public User addUser(User user) throws IOException {
        return usersRepo.addUser(user);
    }

    public User authenticateUser(String email, String password) throws IOException {
        return usersRepo.authenticateUser(email, password);
    }
}
