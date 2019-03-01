package com.cnhutong.cs.mobile.web.controller;

import com.cnhutong.cs.mobile.domain.User;
import com.cnhutong.cs.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author     ：zzl.
 * @ Date       ：Created in 11:48 2019/2/21
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Controller
public class TestC {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        userService.query();
        User u = new User();
        u.setId("123");
        u.setUserName("123");
        this.userService.insertUser(u);
        return "test";
    }
}
