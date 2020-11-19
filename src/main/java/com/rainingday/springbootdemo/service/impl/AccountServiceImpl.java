package com.rainingday.springbootdemo.service.impl;

import com.rainingday.springbootdemo.dao.AccountRepository;
import com.rainingday.springbootdemo.dao.CustomerRepository;
import com.rainingday.springbootdemo.entity.Account;
import com.rainingday.springbootdemo.entity.Customer;
import com.rainingday.springbootdemo.entity.Transaction;
import com.rainingday.springbootdemo.service.AccountService;
import com.rainingday.springbootdemo.view.ResultEnums;
import com.rainingday.springbootdemo.view.ResultView;
import com.rainingday.springbootdemo.view.ResultViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

/**
 * @version 1.8
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author James
 * @date 2020/6/11 11:36
 */
@Async("taskExecutor")
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CompletableFuture<Boolean> saveMoney(Integer customerId, BigDecimal accountAmount) {
        Account account = accountRepository.findAccountByCustomerId(customerId);
        Boolean result = null;
        if (account == null){
            //插入
            account = new Account();
            account.setAccountAmount(accountAmount);
            account.setCustomerId(customerId);
            if(accountRepository.save(account) != null){
                result = true;
            }
        }else{
            //更新
            //在原来账户余额的基础上加上现在的金额，重新赋值
            account.setAccountAmount(account.getAccountAmount().add(accountAmount));
            if(accountRepository.save(account) != null){
                result = true;
            }
        }
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public CompletableFuture<Boolean> collectMoney(Integer customerId, BigDecimal accountAmount) {
        Account account = accountRepository.findAccountByCustomerId(customerId);
        Boolean result = null;
        if(account != null){
            //如果当前账户余额大于取款金额
            if(account.getAccountAmount().compareTo(accountAmount) == 1){
                account.setAccountAmount(account.getAccountAmount().subtract(accountAmount));
                if(accountRepository.save(account) !=  null){
                    result = true;
                }
            }
        }
        return CompletableFuture.completedFuture(result);
    }

    @Transactional
    @Override
    public CompletableFuture<Boolean> transferMoney(Transaction transaction, String PayeeAccountName) {
        System.out.println(transaction);
        Boolean result = false;
        //判断收款人customer是否存在
        Customer customer = customerRepository.findCustomerByCustomerName(PayeeAccountName);
        if (customer != null){
            Account account = accountRepository.findAccountByCustomerId(transaction.getAccountId());
            //判断收款人账户account是否存在
            if(account != null){
                //判断当前账户是否存在
                Account account1 = accountRepository.findAccountByCustomerId(transaction.getAccountId());
                if(account1 != null){
                    //判断当前账户余额是否大于转账金额
                    if (account1.getAccountAmount().compareTo(transaction.getAmount()) == 1){
                        //修改收款人账户余额
                        account.setAccountAmount(account.getAccountAmount().add(transaction.getAmount()));
                        accountRepository.save(account);
                        //修改当前账户余额
                        account1.setAccountAmount(account1.getAccountAmount().subtract(transaction.getAmount()));
                        accountRepository.save(account1);

                        result = true;
                    }
                }
            }
        }
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public CompletableFuture<Account> showAccountInformation(Integer customerId) {
        return CompletableFuture.completedFuture(accountRepository.findAccountByCustomerId(customerId));
    }

    @Override
    public CompletableFuture<String> removeAccount(String accountId) throws InterruptedException {
        for (int i = 1;i <= 10;i++){
            System.out.println(Thread.currentThread().getName()+"----------异步：>"+i);
        }
        Thread.sleep(100000);
        String role = "cutomer";
        return CompletableFuture.completedFuture(role);
    }
}
