package com.rainingday.springbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.8
 * @ClassName Role
 * @Description TODO
 * @Author James
 * @date 2020/6/18 15:45
 */
@Entity
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 7419229779731522702L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CustomerRole> userRoles = new ArrayList<>();
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CustomerRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<CustomerRole> userRoles) {
        this.userRoles = userRoles;
    }
}
