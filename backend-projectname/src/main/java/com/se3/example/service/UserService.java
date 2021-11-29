package com.se3.example.service;

import com.se3.example.entity.User;

public interface UserService {

    /*
    * 验证密码
    * @Param uid
    * */
    void check(User user);
}
