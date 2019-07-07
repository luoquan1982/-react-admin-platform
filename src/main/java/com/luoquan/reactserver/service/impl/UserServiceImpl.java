package com.luoquan.reactserver.service.impl;

import com.luoquan.reactserver.entity.User;
import com.luoquan.reactserver.mapper.UserDao;
import com.luoquan.reactserver.service.UserService;
import com.luoquan.reactserver.util.RetCode;
import com.luoquan.reactserver.util.SuperUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public RetCode userExist(Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");

        if (StringUtils.isAnyBlank(username, password)) {
            return new RetCode(400,"用户名或密码不能为空");
        }
        User user = userDao.verifyUser(map);
        RetCode retCode;
        if (null != user) {
            List<User> data = new ArrayList<>();
            data.add(user);
            retCode = RetCode.success(data);
        } else {
            retCode = new RetCode(406,"用户名或密码错误");
        }
        return retCode;
    }

    @Override
    public RetCode addUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String phone = user.getPhone();
        if (StringUtils.isAnyBlank(username, password, email, phone)) {
            return new RetCode(401,"字段名不能为空");
        }

        try {
            RetCode retCode = this.checkUsername(username);
            if (200 != retCode.getStatus()) {
                return retCode;
            }
            String userId = SuperUtil.getUUID(24);
            user.setId(userId);
            userDao.addUser(user);
        } catch (Exception e) {
            return new RetCode(-1,"服务器异常");
        }
        return RetCode.success();
    }

    @Override
    public RetCode checkUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return new RetCode(401,"字段名不能为空");
        }
        try {
            boolean usernamneExist = userDao.checkUserExist(username);
            if (usernamneExist) {
                return new RetCode(402,"用户名已存在");
            }
        } catch (Exception e) {
            return new RetCode(-1,"服务器异常");
        }
        return RetCode.success();
    }
}
