package com.rainingday.springbootdemo.dao;

import com.rainingday.springbootdemo.entity.Customer;
import com.rainingday.springbootdemo.entity.CustomerRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRoleRepository extends CrudRepository<CustomerRole,Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customer_role(customer_cutomer_id,role_id) VALUES(?1, ?2)", nativeQuery = true)
    void saveABRelation(Integer aId, long bId);

}
