package com.rainingday.springbootdemo.dao;

import com.rainingday.springbootdemo.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    Customer findCustomerByCustomerName(String customerName);


}
