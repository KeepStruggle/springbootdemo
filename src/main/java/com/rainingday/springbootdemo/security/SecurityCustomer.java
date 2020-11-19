package com.rainingday.springbootdemo.security;

import com.rainingday.springbootdemo.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @version 1.8
 * @ClassName SecurityCustomer
 * @Description TODO
 * @Author James
 * @date 2020/6/17 15:00
 */
public class SecurityCustomer implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public SecurityCustomer(Customer customer) {
        this.id = customer.getCustomerId();
        this.username = customer.getCustomerName();
        this.password = customer.getCustomerPassword();
        this.enabled = customer.getEnabled();
        this.authorities = customer.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
