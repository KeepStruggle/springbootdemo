package com.rainingday.springbootdemo.service.impl;

import com.rainingday.springbootdemo.entity.User;
import com.rainingday.springbootdemo.dao.UserRepository;
import com.rainingday.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @version 1.8
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author James
 * @date 2020/6/10 11:57
 */
@Async("taskExecutor")
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<String> authentication(User user) {
        String role = "cutomer";
        return CompletableFuture.completedFuture(role);
    }

    @Override
    public boolean modify(User user) {
        return true;
    }

}
