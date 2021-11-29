package com.se3.example.service.Impl;

import com.se3.example.Repository.UserRepository;
import com.se3.example.entity.User;
import com.se3.example.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void check(User user){



    }
}
