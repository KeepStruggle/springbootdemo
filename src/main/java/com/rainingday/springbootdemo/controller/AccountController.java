package com.rainingday.springbootdemo.controller;

import com.rainingday.springbootdemo.entity.Account;
import com.rainingday.springbootdemo.entity.Audience;
import com.rainingday.springbootdemo.entity.Customer;
import com.rainingday.springbootdemo.entity.Transaction;
import com.rainingday.springbootdemo.service.CustomerService;
import com.rainingday.springbootdemo.util.JwtTokenUtil;
import com.rainingday.springbootdemo.view.ResultEnums;
import com.rainingday.springbootdemo.view.ResultViewFactory;
import com.rainingday.springbootdemo.service.AccountService;
import com.rainingday.springbootdemo.view.ResultView;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;

/**
 * @version 1.8
 * @ClassName AccountController
 * @Description TODO
 * @Author James
 * @date 2020/6/11 11:03
 */
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Audience audience;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * @Description: 根据顾客id查找账户信息
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/11 16:29
     */
    @GetMapping(path = "/accountInformation")
    public ResultView accountInformation(@RequestParam(value = "customerId") Integer customerId) throws ExecutionException, InterruptedException {
        /*String  authorization = request.getHeader("Authorization");
        //提取token
        String token = authorization.replace("Bearer ","");
        //解析token
        Claims claims = JwtTokenUtil.parseJWT(token,audience.getBase64Secret());
        System.out.println(claims);

        ResultView resultView = null;
        String customerName = claims.getSubject();

        //根据token中的顾客名获取顾客的信息
        Customer customer = customerService.findCustomerByCustomerName(claims.getSubject()).get();*/

        ResultView resultView = null;
        //根据顾客id获取账号信息
        Account account = accountService.showAccountInformation(customerId).get();
        if(account!=null){
            resultView = ResultViewFactory.getInstanceViewOfAccount(account);
        }else {
            resultView = ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
        }
        return resultView;
    }

    /**
     * @Description: 取款
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/11 16:31
     *
     * @CrossOrigin中的2个参数：
     *  origin:允许可访问的域列表
     *  maxAge:准备相应前的缓存持续的最大时间（以秒为单位）
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(path = "/withdrew")
    public ResultView withdrewMoney(@RequestParam(value = "customerId") Integer customerId,
                                    @RequestParam(value = "amount") BigDecimal amount) throws ExecutionException, InterruptedException {
        if(accountService.collectMoney(customerId,amount).get()){
            return ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
        }
        return ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
    }

    /**
     * @Description: 转账
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/11 16:38
     */
    @PostMapping(path = "/transfer")
    public ResultView transferMoney(@RequestParam(value = "customerId") Integer customerId,
              @RequestParam(value = "PayeeAccountName") String PayeeAccountName, @RequestParam(value = "amount") BigDecimal amount) throws ExecutionException, InterruptedException {

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAccountId(customerId);
        if (accountService.transferMoney(transaction,PayeeAccountName).get()){
            return ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
        }
        return ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
    }

    /**
     * @Description: 存款
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/13 10:24
     */
    @PostMapping(path = "/deposit")
    public ResultView depositMoney(@RequestParam(value = "customerId") Integer customerId,
                               @RequestParam(value = "amount") BigDecimal amount) throws ExecutionException, InterruptedException {
       if(accountService.saveMoney(customerId,amount).get()){
           return ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
       }
        return ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
    }

    @GetMapping(path = "/remove")
    public ResultView removeAccount(@RequestParam(name = "accountId",defaultValue = "none")String accountId) throws ExecutionException, InterruptedException {
        ResultView resultView = null;
        String NONE_ACCOUNT = "none";
        if(!NONE_ACCOUNT.equals(accountId)){
            resultView = ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
           // resultView.setData(accountService.removeAccount(accountId).get());
            String role = accountService.removeAccount(accountId).get();
            //System.out.println(role);
            System.out.println("=====>"+Thread.currentThread().getName());

        }else {
            resultView = ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
        }
        return resultView;
    }
}
