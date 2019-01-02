package com.xcx.xestore.web.controller;

import com.xcx.xestore.common.pojo.vo.XResult;
import com.xcx.xestore.common.pojo.User;
import com.xcx.xestore.common.util.DateUtils;
import com.xcx.xestore.manager.redis.RedisManager;
import com.xcx.xestore.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 *
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisManager redisManager;

    @RequestMapping(value = "/showRegister")
    public String registerView(){
        return "/user/register";
    }

    @RequestMapping(value = "/showLogin")
    public String loginView(){
        return "/user/login";
    }

    @RequestMapping(value = "showUserDetail")
    public String userDetailView(){
        return "/user/detail";
    }



    @PostMapping(value = "/login")
    public XResult login(HttpServletRequest request, Model model, User user){
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        subject.login(token);

        return null;
    }

    @PostMapping(value = "/register")
    public XResult register(User user){

        userService.registerUser(user);


        XResult xResult = new XResult(11,"ss",user);


        return xResult;
    }

    @GetMapping(value = "/isUsernameExists/{username}")
    public XResult isUsernameExists(@PathVariable String username){

        return userService.isUsernameExists(username);
    }

}
