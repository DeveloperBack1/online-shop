package com.schneider.onlineshop.service;


import com.schneider.onlineshop.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1L, "Ivan","ivan@yahoo.com",
                "+4916532456743","32FGD","ADMINISTRATOR"));
        users.add(new User(2L, "Igor","igor@yahoo.com", "+4916532456755",
                "32JHD","PROGRAMMER1"));
        users.add(new User(3L, "nata","nata@yahoo.com",
                "+4916532456781","32DSD","PROGRAMMER2"));
        users.add(new User(4L, "max","max@yahoo.com",
                "+4916532434581","11DSD","PROGRAMMER3"));



    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getUserID().equals(id))
                .findFirst()
                .orElse(null);
    }


    public User addUser(User user) {
        user.setUserID((long) (users.size() + 1));
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
        }
        return user;
    }

    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getUserID().equals(id));
    }
}

