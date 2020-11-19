package com.rainingday.springbootdemo;

import com.rainingday.springbootdemo.controller.AccountController;
import com.rainingday.springbootdemo.controller.CustomerController;
import com.rainingday.springbootdemo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.8
 * @ClassName SmokeTest
 * @Description TODO
 * @Author James
 * @date 2020/6/11 9:50
 */
@SpringBootTest
public class SmokeTest {

    @Autowired
    private UserController userController;
    @Autowired
    private AccountController accountController;
    @Autowired
    private CustomerController customerController;

    @Test
    public void contextLoads() throws Exception{
        assertThat(userController).isNotNull();
        assertThat(accountController).isNotNull();
        assertThat(customerController).isNotNull();
    }


}
