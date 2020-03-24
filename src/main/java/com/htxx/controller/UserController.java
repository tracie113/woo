package com.htxx.controller;

import com.htxx.entity.User;
import com.htxx.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceI userServiceI;

    @RequestMapping("/hello")
    public String hello(){
        return userServiceI.hello();
    }

    @RequestMapping("/selectAll")
    public String selectAll(){
        return userServiceI.selectAll().toString();
    }

    @RequestMapping("/selectNameById/{id}")
    public String selectNameById(@PathVariable String id){
        return userServiceI.selectNameById(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody User u){
        boolean flag = userServiceI.login(u);
        return flag ? "1" :"0";
    }

}
