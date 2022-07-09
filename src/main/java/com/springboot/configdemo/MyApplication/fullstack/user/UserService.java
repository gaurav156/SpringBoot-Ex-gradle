package com.springboot.configdemo.MyApplication.fullstack.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    Logger logger = LoggerFactory.getLogger(getClass());

    public List<User> getUserList() throws IOException {
        logger.debug("User Service - getUserList method call");
        return usersRepo.getUserList();
    }

    public User userFilter(String id) throws IOException {
        logger.debug(String.format("User Service - userFilter method call for userID : %s", id));
        return usersRepo.userFilter(id);
    }

    public User addUser(User user) throws IOException {
        logger.debug(String.format("User Service - addUser method call for userID : %s", user.getId()));
        return usersRepo.addUser(user);
    }

    public User authenticateUser(String email, String password) throws IOException {
        logger.debug("User Service - authenticateUser method call");
        return usersRepo.authenticateUser(email, password);
    }
}
