package com.rainingday.springbootdemo.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @version 1.8
 * @ClassName CustomerRole
 * @Description TODO
 * @Author James
 * @date 2020/6/27 8:45
 */
@Entity
@NoArgsConstructor
@Table(name = "customer_role")
public class CustomerRole implements Serializable {
    private static final long serialVersionUID = 7419229779731522702L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Role role;
    public CustomerRole(Customer customer, Role role) {
        this.customer = customer;
        this.role = role;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
