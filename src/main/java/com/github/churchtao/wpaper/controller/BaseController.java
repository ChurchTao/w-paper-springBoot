package com.github.churchtao.wpaper.controller;

import com.github.churchtao.wpaper.context.DataContext;

import javax.servlet.http.HttpSession;

/**
 * @author taojiacheng
 * @create 2018-05-29 14:58
 **/
public abstract class BaseController {

    public HttpSession getSession(){
        return DataContext.getSession();
    }
}
