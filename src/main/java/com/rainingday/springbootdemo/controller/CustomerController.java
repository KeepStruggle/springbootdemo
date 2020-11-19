package com.rainingday.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.rainingday.springbootdemo.annotation.JwtIgnore;
import com.rainingday.springbootdemo.entity.Audience;
import com.rainingday.springbootdemo.entity.Customer;
import com.rainingday.springbootdemo.security.CustomerDetailsServiceImpl;
import com.rainingday.springbootdemo.util.JwtTokenUtil;
import com.rainingday.springbootdemo.view.ResultViewFactory;
import com.rainingday.springbootdemo.service.CustomerService;
import com.rainingday.springbootdemo.view.ResultEnums;
import com.rainingday.springbootdemo.view.ResultView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @version 1.8
 * @ClassName CustomerController
 * @Description TODO
 * @Author James
 * @date 2020/6/11 9:54
 */
@Slf4j
@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerDetailsServiceImpl customerDetailsServiceImpl;

    @Autowired
    private Audience audience;


    /**
     * @Description: 顾客登录
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/13 11:08
     */
    @GetMapping(path = "/login")
    @JwtIgnore
    public ResultView customerLogin(HttpServletResponse response,
                                    @RequestParam(value = "name") String customerName,
                                    @RequestParam(value = "password")String customerPassword) throws ExecutionException, InterruptedException {

        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerPassword(customerPassword);
        Customer customer1 = customerService.authentication(customer).get();
        //System.out.println("customer:"+customer1);

        //用户验证
        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(customerName,customerPassword);
        Authentication authentication = auth.authenticate(authenticationToken);
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = customerDetailsServiceImpl.loadUserByUsername(customerName);
        System.out.println(userDetails.toString());

        String role = "";
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for(GrantedAuthority authoritie : authorities){
            role += authoritie.getAuthority();
            System.out.println(role);
        }
        ResultView resultView = null;

        if(authentication.isAuthenticated()){
                // 创建token ----- JWT
                String token = JwtTokenUtil.createJWT(  userDetails.getUsername()
                        , userDetails.getPassword()
                        , role
                        , audience);
                log.info("### 登录成功, token={} ###", token);

                // 将token放在响应头
                response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
                log.info("customer login");
                JSONObject result = new JSONObject();
                result.put("token", token);
                result.put("customerName",userDetails.getUsername());
                result.put("customerId",customer1.getCustomerId());
                result.put("authentication",authentication);
                resultView = ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
                resultView.setData(result);
        }else {
            resultView = ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
        }
        return resultView;

    }

    /**
     * @Description: 顾客注册
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/13 11:08
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(path = "/register")
    @JwtIgnore
    public ResultView customerRegister(@RequestParam(value = "customerName") String customerName,
                @RequestParam String customerPassword) throws Exception{
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerPassword(bCryptPasswordEncoder.encode(customerPassword));
        ResultView resultView = new ResultView();
        Boolean result = customerService.saveCustomer(customer).get();

        if(result){
            resultView = ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
            resultView.setData(result);
        }else {
            resultView = ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
        }
        return resultView;
    }

    /**
     * @Description: 顾客信息修改
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/13 11:08
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(path = "/modify")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResultView customerModify(@RequestParam String customerName,
                                     @RequestParam String customerPassword,@RequestParam Integer customerId) throws ExecutionException, InterruptedException {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerPassword(bCryptPasswordEncoder.encode(customerPassword));
        customer.setCustomerId(customerId);
        if(customerService.modifyCustomer(customer).get()){
            return ResultViewFactory.getInstanceViewOfCustomer(customerName);
        }
        return ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
    }

    /**
     * @Description: 查看所有顾客信息
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/13 11:09
     */
    @GetMapping(path = "/list")
    public ResultView customerList(){
        return null;
    }
}
