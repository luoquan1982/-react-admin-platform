package com.luoquan.reactserver.controller;

import com.luoquan.reactserver.entity.User;
import com.luoquan.reactserver.service.UserService;
import com.luoquan.reactserver.util.RetCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.spi.http.HttpHandler;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public RetCode<User> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return userService.userExist(map);
    }

    @PostMapping("/manage/user/add")
    public RetCode addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
