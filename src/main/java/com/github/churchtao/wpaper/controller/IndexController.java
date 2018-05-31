package com.github.churchtao.wpaper.controller;

import com.github.churchtao.wpaper.context.DataContext;
import com.github.churchtao.wpaper.dao.UserRepository;
import com.github.churchtao.wpaper.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taojiacheng
 * @create 2018-05-29 14:31
 **/
@RestController
public class IndexController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/index")
    public RestResponse index(){
//        System.out.println(DataContext.getSession().getId());
//        System.out.println(DataContext.Cookie.get("JSESSIONID").toString());
        return RestResponse.ok(userRepository.findAll(),200,"Server run well!");
    }
}
