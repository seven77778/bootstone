package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.service.login.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lsh on 2019/2/12.
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @Resource
    public CommonService commonservice;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String login() {
        return "index";
    }

    @RequestMapping(value = "/loginPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request, HttpSession session) {
        String tno = request.getParameter("name");
        String password = request.getParameter("pw");
        System.out.println("你输入的用户名为：" + tno);
        System.out.println("你输入的密码为：" + password);
        session.setAttribute("tname", "");
        if(commonservice.login(tno, password)){
            return "登录成功";
        } else {
            return "登录失败";
        }
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginindex() {
        return "/login/test";

    }

}
