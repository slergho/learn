package com.example.learn.controller;

import com.example.learn.entity.SysUser;
import com.example.learn.security.WebSecurityConfig;
import com.example.learn.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @GetMapping(value = "/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account, Model model){

        return "index";
    }
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
    @ResponseBody
    @PostMapping(value = "/loginVerify")
    public String loginVerify(String username,String password,HttpSession session){
        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(password);

        boolean verify = loginServiceImpl.verifyLogin(user);
        if (verify) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}
