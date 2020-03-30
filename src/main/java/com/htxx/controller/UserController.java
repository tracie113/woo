package com.htxx.controller;

import com.htxx.common.ResponseMessage;
import com.htxx.entity.User;
import com.htxx.service.UserServiceI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserServiceI userServiceI;


    @Autowired
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
    public ResponseMessage login(HttpServletRequest request, @RequestBody User u){
        User user = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(u.getUsername(),u.getPassword());
        try {
            subject.login(usernamePasswordToken);
            user = userServiceI.login(u);
            if(subject.hasRole("admin")){
                if(subject.isPermitted("view")){
                    return responseMessage(user);
                }else{
                    return responseMessage(false,"当前角色权限不足！");
                }
            }else{
                return responseMessage(false,"当前角色不允许登录！");
            }
        }catch (UnknownAccountException e1){
            return responseMessage(false,"账户名称错误！");
        }catch (IncorrectCredentialsException e2){
            return responseMessage(false,"登录密码错误！");
        }

    }

    @ResponseBody
    @GetMapping("/getPassword/{username}")
    public String getPass(@PathVariable String username){
        return userServiceI.selectPasswordByUsername(username);
    }

}
