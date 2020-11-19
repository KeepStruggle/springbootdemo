package com.rainingday.springbootdemo.view;

import com.rainingday.springbootdemo.entity.Account;

/**
 * @version 1.8
 * @ClassName ResultViewFactory
 * @Description TODO
 * @Author James
 * @date 2020/6/11 15:55
 */
public class ResultViewFactory {
    private ResultViewFactory() {
    }

    public static ResultView getInstanceView(ResultEnums enums){
        return new ResultView(enums.getCode(),enums.getMessage());
    }

    public static ResultView getInstanceViewOfAccount(Account account){
        ResultView resultView = null;
        if(account != null){
            resultView = ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
            resultView.setData(account);
        }else {
            resultView = ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
        }
        return resultView;
    }

    public static ResultView getInstanceViewOfCustomer(String customerId){
        ResultView resultView = null;
        if(customerId != null){
            resultView = ResultViewFactory.getInstanceView(ResultEnums.SUCCESS);
            resultView.setData(customerId);
        }else {
            resultView = ResultViewFactory.getInstanceView(ResultEnums.FAILURE);
        }
        return resultView;
    }


}
