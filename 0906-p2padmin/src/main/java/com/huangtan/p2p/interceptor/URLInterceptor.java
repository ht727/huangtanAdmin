package com.huangtan.p2p.interceptor;

import com.huangtan.p2p.model.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class URLInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("sessionuser");
        if(null == userInfo){
            request.setAttribute("noLogin","请您先登录");
            request.getRequestDispatcher("/login").forward(request,response);
            return  false;
        }
        //获取用户请求路径
        String servletPath = request.getServletPath();

        Map<String, String[]> parameterMap = request.getParameterMap();
        //拼接请求字符串,根本就沒必要
        StringBuilder stringBuilder = new StringBuilder();
        if(parameterMap!=null && parameterMap.size() >0) {
            for (String key : parameterMap.keySet()) {
                String[] strings = parameterMap.get(key);
                stringBuilder.append(key + "=" + strings[0]);
                stringBuilder.append("&");
            }
            //删除最后一个&，需要先进行判断是否存在
             stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        //从用户中获取url集合
        Map<String, String> urlMap = userInfo.getUrlMap();
        //判断请求路径被包含
        if(!urlMap.containsKey(servletPath)){
            request.getRequestDispatcher("/noPermission").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
