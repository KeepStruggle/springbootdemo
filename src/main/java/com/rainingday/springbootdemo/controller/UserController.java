package com.rainingday.springbootdemo.controller;

import com.rainingday.springbootdemo.entity.User;
import com.rainingday.springbootdemo.view.ResultViewFactory;
import com.rainingday.springbootdemo.service.UserService;
import com.rainingday.springbootdemo.view.ResultEnums;
import com.rainingday.springbootdemo.view.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.8
 * @ClassName UserController
 * @Description TODO
 * @Author James
 * @date 2020/6/10 11:56
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @Description: 用户登录
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/13 11:09
     */
    @GetMapping(path = "/login")
    public ResultView userLogin(@RequestParam(value = "userName") String userName,@RequestParam(value = "userPassword") String userPassword){
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        ResultView resultView = null;
        userService.authentication(user);
        return resultView;
    }

    /**
     * @Description: 用户信息修改
     * @Param: 
     * @return: 
     * @Author: James
     * @Date: 2020/6/13 11:09
     */
    @GetMapping(path = "/Modify")
    public ResultView userModify(@RequestBody User user){
        ResultView resultView = null;
        Boolean aBoolean= userService.modify(user);
        if(aBoolean){
            resultView = ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
            resultView.setData(aBoolean);
            return resultView;
        }else{
            resultView = ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
            return resultView;
        }
    }
}
