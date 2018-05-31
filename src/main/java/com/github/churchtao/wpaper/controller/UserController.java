package com.github.churchtao.wpaper.controller;



import com.github.churchtao.wpaper.constant.BaseConst;
import com.github.churchtao.wpaper.constant.LogAction;
import com.github.churchtao.wpaper.entity.User;
import com.github.churchtao.wpaper.service.LogService;
import com.github.churchtao.wpaper.service.UserService;
import com.github.churchtao.wpaper.util.CodecUtil;
import com.github.churchtao.wpaper.util.RequestUtil;
import com.github.churchtao.wpaper.util.RestResponse;
import com.github.churchtao.wpaper.util.StringUtil;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jiacheng on 2017/8/22.
 */
@RestController
public class UserController extends BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public RestResponse login(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password, HttpServletRequest request) {
        String ip = RequestUtil.getIp(request);
        User user =userService.login(username,password,ip);
        HttpSession session = this.getSession();
        if (StringUtil.isEmpty(user.getToken())){
            String time = String.valueOf(System.currentTimeMillis());
            String token = user.getId()+time;
            user.setToken(CodecUtil.sha1(token));
        }
        userService.loginRemember(user.getId(),user.getToken());
        logService.save(LogAction.LOGIN.toString(),"",ip,user.getNickname(),user.getId());
        session.setAttribute(BaseConst.LOGIN_SESSION_KEY,user);
        return RestResponse.ok(user,200,"登录成功！");
    }

    @RequestMapping(value = "/user/loginByToken",method = RequestMethod.POST)
    public RestResponse loginByToken(@RequestParam(name = "id") String id,
                                     @RequestParam(name = "token") String token, HttpServletRequest request) {
        String ip = RequestUtil.getIp(request);
        User user =userService.loginByToken(Integer.parseInt(id),token);
        HttpSession session = this.getSession();
        logService.save(LogAction.AUTO_LOGIN.toString(),"",ip,user.getNickname(),user.getId());
        session.setAttribute(BaseConst.LOGIN_SESSION_KEY,user);
        return RestResponse.ok(user,200,"登录成功！");
    }

    @RequestMapping(value = "/user/getUserInfo",method = RequestMethod.POST)
    public RestResponse getUserInfo(@RequestParam(name = "id") String id,
                                    @RequestParam(name = "token") String token) {
        User user =userService.loginByToken(Integer.parseInt(id),token);
        return RestResponse.ok(user,200,"成功！");
    }

    @RequestMapping(value = "/user/init",method = RequestMethod.POST)
    public RestResponse init(@RequestParam(name = "username") String username,
                             @RequestParam(name = "phoneOrEmail") String phoneOrEmail,
                             @RequestParam(name = "password") String password, HttpServletRequest request){
        User user =userService.save(username,phoneOrEmail,password,RequestUtil.getIp(request));
        return RestResponse.ok(user,200,"注册成功!");
    }

    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    public RestResponse update(@RequestParam(name = "nickname") String nickname,
                               @RequestParam(name = "gender") String gender,
                               @RequestParam(name = "info") String info,
                               @RequestParam(name = "id") Integer id,
                               @RequestParam(name = "avatar") String avatar){
        int genderInt = "男".equals(gender) ?1:0;
        return RestResponse.ok(userService.update(id,nickname,avatar,genderInt,info),200,"更新成功");
    }

    @RequestMapping(value = "/qiniu/uptoken",method = RequestMethod.GET)
    public RestResponse uptoken(){
        String accessKey = "uySX174_v5dYdzu0rwMcroHZd2AX0dkWGy09MU4B";
        String secretKey = "f-4zRJDtjybxtLTBVisvkjHVg9JbwuI2zoyYoY1i";
        String bucket = "myimagezone";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return RestResponse.ok(upToken,200);
    }
}
