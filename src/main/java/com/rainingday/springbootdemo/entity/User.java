package com.rainingday.springbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @version 1.8
 * @ClassName User
 * @Description TODO
 * @Author James
 * @date 2020/6/8 17:59
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)     //Jackson解析JSON数据时，忽略未知的字段。（如果不设置，当JSON字段和bean字段不匹配时，会抛出异常）
public class User {

    @Id //设置为主键
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Size(min = 1,max = 28,message = "{user.userName.size}")
    @NotBlank(message = "{user.userName.notBlank}")
    @Column(name = "user_name")
    private String userName;

    @Email
    @Column(name = "user_email")
    private String userEmail;

    @NotBlank(message = "{user.userPassword.notBlank}")
    @Column(name = "user_password")
    private String userPassword;

}
