package com.rainingday.springbootdemo.security;

import com.rainingday.springbootdemo.dao.CustomerRepository;
import com.rainingday.springbootdemo.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @version 1.8
 * @ClassName CustomerSecurityServiceImpl
 * @Description TODO    获取用户信息
 * @Author James
 * @date 2020/6/17 14:58
 */
@Service
@Slf4j
public class CustomerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
        //jpa
        //
        Customer customer = customerRepository.findCustomerByCustomerName(customerName);
        SecurityCustomer securityCustomer = new SecurityCustomer(customer);
        return securityCustomer;
    }
}
