package com.rainingday.springbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @version 1.8
 * @ClassName Account
 * @Description TODO
 * @Author James
 * @date 2020/6/11 10:32
 */
@Data
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "account_amount")
    private BigDecimal accountAmount;

    @Column(name = "customerId")
    private Integer customerId;
}
