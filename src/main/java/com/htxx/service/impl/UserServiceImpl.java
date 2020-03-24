package com.htxx.service.impl;

import com.htxx.entity.User;
import com.htxx.mapper.UserMapper;
import com.htxx.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserMapper userMapper;

    @Override
   public List<User> selectAll(){
        return userMapper.selectAll();
    }

    @Override
    public String selectNameById(String id) {
        return userMapper.selectNameById(id);
    }

    @Override
    public String hello() {
        return "Hello World";
    }

    @Override
    public boolean login(User u){
        return userMapper.login(u)>0 ? true : false;
    }
}
