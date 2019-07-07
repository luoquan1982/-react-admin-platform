package com.luoquan.reactserver.entity;

import lombok.Data;

/**
 * @author luoquan
 */
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Role role;
}
