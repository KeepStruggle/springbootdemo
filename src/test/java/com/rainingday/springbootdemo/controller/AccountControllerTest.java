package com.rainingday.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.rainingday.springbootdemo.controller.AccountController;
import com.rainingday.springbootdemo.entity.Account;
import com.rainingday.springbootdemo.entity.Transaction;
import com.rainingday.springbootdemo.service.AccountService;
import com.rainingday.springbootdemo.view.ResultView;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @version 1.8
 * @ClassName AccountControllerTest
 * @Description TODO
 * @Author James
 * @date 2020/6/11 16:52
 */
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void accountCheckingByCustomerId() throws Exception{

        Account account = new Account();
        account.setAccountId(010);
        account.setCustomerId(1);
        account.setAccountAmount(new BigDecimal(100));

        //此处是模拟 service接口的返回值，如下意思是：当service的show方法中的参数是“vip001”，那么它的返回值是对象account，上面创建的
        when(accountService.showAccountInformation(1)).thenReturn(CompletableFuture.completedFuture(account));
        //此处是模拟get请求，在括号中填写测试链接资源，接着注意后面可以跟param方法，将参数值输入到里面
        //Expect是期望值，这里请一定要注意括号的层级 get("/account/accountInformation").param("name","vip001")
        this.mockMvc.perform(get("/account/accountCheckingByCustomerId")
                .param("name","vip001"))
                .andExpect(content().string(containsString("100")));
    }

    @Test
    public void withdrewMoney() throws Exception{
        Transaction transaction = new Transaction();
        transaction.setAccountId(01);
        transaction.setPayeeAccountId(02);
        transaction.setAmount(new BigDecimal("200"));
        String paraJson = JSON.toJSONString(transaction);

        Account account = new Account();
        account.setAccountId(01);
        account.setCustomerId(1);
        account.setAccountAmount(new BigDecimal(300));
        when(accountService.collectMoney(12,new BigDecimal(50))).thenReturn(CompletableFuture.completedFuture(true));
        this.mockMvc.perform(post("/account/withdrewMoney").contentType(MediaType.APPLICATION_JSON).content(paraJson))
                .andExpect(content().string(containsString("vip001")));
    }
}
