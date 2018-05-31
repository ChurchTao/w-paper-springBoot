package com.github.churchtao.wpaper.util;


import com.github.churchtao.wpaper.constant.BaseConst;
import com.github.churchtao.wpaper.context.DataContext;
import com.github.churchtao.wpaper.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jiacheng on 2017/8/21.
 */
public class RequestUtil {
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static User getLoginUser() {
        HttpSession session = DataContext.getSession();
        if (null == session) {
            return null;
        }
        User user = (User) session.getAttribute(BaseConst.LOGIN_SESSION_KEY);
        return user;
    }
}
