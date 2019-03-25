package com.god.seep.controller;

import com.god.seep.bean.User;
import com.god.seep.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;


@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/test")
public class TestController {
    @Resource
    private UserService userService;

    @RequestMapping(value = {"/first"}, method = RequestMethod.GET)
    public String test(int id) {
        System.out.println("controller test id" + id);
        return "welcome";
    }

    @RequestMapping(value = {"/express"}, method = RequestMethod.GET)
    public String test1() {
        return "expressPrompt";
    }

    @RequestMapping(value = {"/recharge"}, method = RequestMethod.GET)
    public String test2() {
        return "rechargePrompt";
    }

    @RequestMapping(value = {"/billing"}, method = RequestMethod.GET)
    public String test3() {
        return "billingDetails";
    }

    @RequestMapping(value = {"/chargeRule"}, method = RequestMethod.GET)
    public String test4() {
        return "chargeRule";
    }

    @ResponseBody
    @RequestMapping(value = "/json")
    public String json() {
        return "{\"result\":[{\"customerWalletId\":\"e549b15965b1c9cd0165b1ca00f20002\",\"customerWalletType\":\"CWT0001\",\"rechargeAmountBalance\":1935.29,\"giftAmountBalance\":349.00,\"frozenAmount\":0.00,\"availableBalance\":2284.29,\"totalBalance\":2284.29}],\"success\":true}\n";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save() {
        User user = new User();
        user.setId(3L);
        user.setNickname("Jack");
        user.setPassword("123456");
        userService.save(user);
        return user.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/load")
    public String load() {
        return userService.load(1L).toString();
    }
}
