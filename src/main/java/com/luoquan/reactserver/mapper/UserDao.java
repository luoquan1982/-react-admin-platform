package com.luoquan.reactserver.mapper;

import com.luoquan.reactserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface UserDao {
    User verifyUser(Map<String,String> map);

    void addUser(User user);

    boolean checkUserExist(String username);
}
