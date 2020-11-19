package com.rainingday.springbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.8
 * @ClassName Customer
 * @Description TODO
 * @Author James
 * @date 2020/6/11 9:54
 */
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable {
    private static final long serialVersionUID = 7419229779731522702L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cutomer_id")
    private Integer customerId;

    @Size(min = 1,max = 28,message = "{customer.customerName.size}")
    @NotBlank(message = "customer.customerName.NotBlank")
    @Column(name = "customer_name")
    private String customerName;

    @NotBlank(message = "customer.customerPassword.NotBlank")
    @Column(name = "customer_password")
    private String customerPassword;

    @Column(columnDefinition = "tinyint(1) default '1'")
    private Boolean enabled;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CustomerRole> customerRoles = new ArrayList<>();

    public List<SimpleGrantedAuthority> getRoles() {
        List<Role> roles = customerRoles.stream().map(CustomerRole::getRole).collect(Collectors.toList());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<CustomerRole> getCustomerRoles() {
        return customerRoles;
    }

    public void setCustomerRoles(List<CustomerRole> customerRoles) {
        this.customerRoles = customerRoles;
    }
}
