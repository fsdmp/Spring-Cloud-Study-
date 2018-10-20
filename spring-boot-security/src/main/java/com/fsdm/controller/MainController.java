package com.fsdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.rmi.MarshalledObject;

/**
 * @author 房上的猫
 * @create 2018-10-19 16:28
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@Controller
public class MainController {
    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/user/index")
    public String userIndex(){
        return "user/index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }
    @RequestMapping("/401")
    public String accessDenied(){
        return "401";
    }



}
