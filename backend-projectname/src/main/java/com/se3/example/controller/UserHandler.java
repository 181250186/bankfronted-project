package com.se3.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.se3.example.Repository.UserRepository;
import com.se3.example.entity.User;
import com.se3.example.result.ResponseVO;
import com.se3.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins={"http://106.14.72.92:8002","http://106.14.72.92:8001","http://106.14.72.92","http://localhost:8001","http://localhost:8002"}, allowCredentials = "true")
public class UserHandler {
    @Autowired
    UserService userService;

    //@PostMapping("")
//    public ResponseVO check(@RequestBody JSONObject jsonObject){
//        User user=new User();
//        user.setUserName(jsonObject.get("username").toString());
//        user.setPassword(jsonObject.get("password").toString());
//        userService.check(user);
//        return ResponseVO.buildSuccess();
//    }
}
