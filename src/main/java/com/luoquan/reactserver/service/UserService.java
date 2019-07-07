package com.luoquan.reactserver.service;

import com.luoquan.reactserver.entity.User;
import com.luoquan.reactserver.util.RetCode;

import java.util.Map;

/**
 * @author luoquan
 */
public interface UserService {
    RetCode<User> userExist(Map<String,String> map);

    RetCode addUser(User user);

    RetCode checkUsername(String username);
}
