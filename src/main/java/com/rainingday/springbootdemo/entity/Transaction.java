package com.rainingday.springbootdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @version 1.8
 * @ClassName Transaction
 * @Description TODO
 * @Author James
 * @date 2020/6/11 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    //本金账户
    private Integer accountId;

    //收款账户
    private Integer payeeAccountId;

    //转款金额
    private BigDecimal amount;
}
