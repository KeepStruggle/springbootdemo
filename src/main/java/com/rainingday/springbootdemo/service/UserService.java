package com.rainingday.springbootdemo.service;

import com.rainingday.springbootdemo.entity.User;

import java.util.concurrent.CompletableFuture;

public interface UserService {

    CompletableFuture<String> authentication(User user);

    boolean modify(User user);


}
