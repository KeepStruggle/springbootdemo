package com.rainingday.springbootdemo.dao;

import com.rainingday.springbootdemo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author James
 * @date 2020/6/10 14:18
 */
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

}
