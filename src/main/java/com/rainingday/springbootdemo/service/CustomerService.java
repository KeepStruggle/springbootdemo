package com.rainingday.springbootdemo.service;

import com.rainingday.springbootdemo.entity.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomerService {

    CompletableFuture<Customer> authentication(Customer customer);

    CompletableFuture<Boolean> saveCustomer(Customer customer);

    CompletableFuture<Boolean> modifyCustomer(Customer customer);

    String removeCustomer(String customerId);

    CompletableFuture<Customer> findCustomerByCustomerName(String customerName);

}
