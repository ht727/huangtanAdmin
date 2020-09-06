package com.huangtan.p2p.controller;


import com.huangtan.p2p.model.UserInfo;
import com.huangtan.p2p.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Resource
    UserInfoService userInfoService;

    @RequestMapping("/")
    public String toLogin(HttpSession session) {
        UserInfo sesssionuser = (UserInfo) session.getAttribute("sesssionuser");
        if (null != sesssionuser) {
            return "main";
        }
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session, Model model) {
        UserInfo userInfo = userInfoService.queryUserInfoByUserNameAndPassword(username, password);
        if (null != userInfo) {
            session.setAttribute("sessionuser", userInfo);
            return "main";
        }
        model.addAttribute("message", "账号或者密码有误");
        return "login";
    }
}
