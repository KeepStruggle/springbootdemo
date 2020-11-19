package com.rainingday.springbootdemo.service;

import com.rainingday.springbootdemo.entity.Account;
import com.rainingday.springbootdemo.entity.Transaction;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public interface AccountService {

    CompletableFuture<Boolean> saveMoney(Integer customerId, BigDecimal accountAmount);

    CompletableFuture<Boolean> collectMoney(Integer customerId, BigDecimal accountAmount);

    CompletableFuture<Boolean> transferMoney(Transaction transaction, String PayeeAccountName);

    CompletableFuture<Account> showAccountInformation(Integer customerId);

    CompletableFuture<String> removeAccount(String accountId) throws InterruptedException;
}
