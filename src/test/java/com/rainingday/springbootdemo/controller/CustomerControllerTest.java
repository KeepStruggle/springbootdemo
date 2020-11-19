package com.rainingday.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.rainingday.springbootdemo.controller.CustomerController;
import com.rainingday.springbootdemo.entity.Audience;
import com.rainingday.springbootdemo.entity.Customer;
import com.rainingday.springbootdemo.service.AccountService;
import com.rainingday.springbootdemo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @TestConfiguration
    static class MyTestConfiguration{

        @Bean
        Audience createAudience(){
            return new Audience();
        }
    }

    @Test
    void customerLogin() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerName("James");
        customer.setCustomerPassword("123");
        String paraJson = JSON.toJSONString(customer);

        //此处是模拟 service接口的返回值，如下意思是：当service的show方法中的参数是“vip001”，那么它的返回值是对象customer，上面创建的
        when(customerService.authentication(customer)).thenReturn(CompletableFuture.completedFuture(customer));
        //此处是模拟get请求，在括号中填写测试链接资源，接着注意后面可以跟param方法，将参数值输入到里面
        //Expect是期望值，这里请一定要注意括号的层级 get("/account/accountInformation").param("name","vip001")
        this.mockMvc.perform(get("/customer/login")
                .param("name","James").param("password","123"))
                .andExpect(content().string(containsString("0")));
    }

    @Test
    void customerRegister() {
    }

    @Test
    void customerModify() {
    }

    @Test
    void customerList() {
    }
}