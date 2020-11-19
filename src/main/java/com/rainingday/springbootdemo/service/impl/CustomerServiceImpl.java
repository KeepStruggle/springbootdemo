package com.rainingday.springbootdemo.service.impl;

import com.rainingday.springbootdemo.dao.CustomerRepository;
import com.rainingday.springbootdemo.dao.CustomerRoleRepository;
import com.rainingday.springbootdemo.dao.RoleRepository;
import com.rainingday.springbootdemo.entity.Customer;
import com.rainingday.springbootdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;

/**
 * @version 1.8
 * @ClassName CustomerServiceImpl
 * @Description TODO
 * @Author James
 * @date 2020/6/11 9:55
 */
@Async("taskExecutor")
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRoleRepository customerRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CompletableFuture<Customer> authentication(Customer customer) {
        return CompletableFuture.completedFuture(customerRepository.findCustomerByCustomerName(customer.getCustomerName()));
    }

    @Transactional
    @Override
    public CompletableFuture<Boolean> saveCustomer(Customer customer) {
        Boolean result = false;
        if(customerRepository.findCustomerByCustomerName(customer.getCustomerName())==null){
            customerRepository.save(customer);
            customerRoleRepository.saveABRelation(customerRepository.findCustomerByCustomerName(customer.getCustomerName()).getCustomerId()
                    ,roleRepository.findRoleByName("USER").getId());
            result = true;
        }
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public CompletableFuture<Boolean> modifyCustomer(Customer customer) {
        Boolean result = false;
        if(customerRepository.save(customer)!=null){
            result = true;
        }
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public String removeCustomer(String customerId) {
        return null;
    }

    @Override
    public CompletableFuture<Customer> findCustomerByCustomerName(String customerName) {
        return CompletableFuture.completedFuture(customerRepository.findCustomerByCustomerName(customerName));
    }


}
