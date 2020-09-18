package com.huangtan.p2p.controller;


import com.huangtan.p2p.service.RoleInfoService;
import com.huangtan.p2p.cookie.CookieUtils;
import com.huangtan.p2p.model.RoleInfo;
import com.huangtan.p2p.model.UserInfo;
import com.huangtan.p2p.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Resource
    UserInfoService userInfoService;

    @Resource
    RoleInfoService roleInfoService;

    @RequestMapping("/")
    public String toLogin(HttpSession session,HttpServletRequest request) {
        String username = CookieUtils.getCookieValue(request, "username");
        String password = CookieUtils.getCookieValue(request, "password");
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            UserInfo userInfo = userInfoService.queryUserInfoByUserNameAndPassword(username, password);
            session.setAttribute("sessionuser", userInfo);
            return "main";
        }
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, String isOk,HttpServletRequest request, HttpServletResponse response
                        , HttpSession session, Model model) {
        UserInfo userInfo = userInfoService.queryUserInfoByUserNameAndPassword(username, password);
        if (null != userInfo) {
            session.setAttribute("sessionuser", userInfo);
            Cookie[] cookies = request.getCookies();
            //用戶勾选记住密码，添加到cookie中
            if(null != isOk && StringUtils.equals(isOk,"on")) {
                if (null == cookies) {
                    model.addAttribute("message", "请打开cookie");
                    return "login";
                }
                CookieUtils.addCookie("username", username, request, response,7*24*60*60);
                CookieUtils.addCookie("password", password, request, response,7*24*60*60);
            }else{
                //用户不勾选则删除cookie，直接选择覆盖即可
                CookieUtils.addCookie("username", username, request, response,0);
                CookieUtils.addCookie("password", password, request, response,0);
            }
            return "main";
        }
        model.addAttribute("message", "账号或者密码有误");
        return "login";
    }

    @RequestMapping("/noPermission")
    public String noPermission(Model model){
        model.addAttribute("error","您暂无权限");
        return "noPermission";
    }

    @RequestMapping("/admin/roles")
    public String roles(Model model){
        //查询所有的角色信息
        List<RoleInfo> roleInfoList = roleInfoService.queryAllRoleInfo();

        //将结果保存到request中
        model.addAttribute("roleInfoList",roleInfoList);
        //请求转发
        return "roles";
    }

    @RequestMapping("/admin/toDisPerms")
    public String toDisPerms(Integer roleId){
        return "disPerms";
    }
}
