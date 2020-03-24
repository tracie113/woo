package com.htxx.service;

import com.htxx.entity.User;

import java.util.List;

public interface UserServiceI {
    String hello();
    List<User> selectAll();
    String selectNameById(String id);
    boolean login(User u);
}
