package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.service.login.LoginService;
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
    public LoginService loginService;

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
        if(loginService.login(tno, password)){
            return "登录成功";
        } else {
            return "登录失败";
        }
    }

    @RequestMapping(value = "/marry", method = {RequestMethod.POST, RequestMethod.GET})
    public String marry(HttpServletRequest request, HttpSession session) {
        String age = request.getParameter("age");
        System.out.println("你输入的年龄为：" + age);
        session.setAttribute("tname", "");
        if(loginService.marry(age)){
            return "验证通过，可以结婚";
        } else {
            return "验证失败，无法结婚";
        }
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginindex() {
        return "/login/test";

    }

}
