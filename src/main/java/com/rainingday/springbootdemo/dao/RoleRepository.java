package com.rainingday.springbootdemo.dao;

import com.rainingday.springbootdemo.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findRoleByName(String name);
}
