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
import org.springframework.web.bind.annotation.*;

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
    public RestResponse login(@RequestBody LoginObj loginObj, HttpServletRequest request) {
        String ip = RequestUtil.getIp(request);
        User user =userService.login(loginObj.getUsername(),loginObj.getPassword(),ip);
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
    public RestResponse loginByToken(@RequestBody LoginObj loginObj, HttpServletRequest request) {
        String ip = RequestUtil.getIp(request);
        User user =userService.loginByToken(loginObj.getId(),loginObj.getToken());
        HttpSession session = this.getSession();
        logService.save(LogAction.AUTO_LOGIN.toString(),"",ip,user.getNickname(),user.getId());
        session.setAttribute(BaseConst.LOGIN_SESSION_KEY,user);
        return RestResponse.ok(user,200,"登录成功！");
    }

    @RequestMapping(value = "/user/getUserInfo",method = RequestMethod.POST)
    public RestResponse getUserInfo(@RequestBody LoginObj loginObj) {
        User user =userService.loginByToken(loginObj.getId(),loginObj.getToken());
        return RestResponse.ok(user,200,"成功！");
    }

    @RequestMapping(value = "/user/init",method = RequestMethod.POST)
    public RestResponse init(@RequestBody LoginObj loginObj, HttpServletRequest request){
        User user =userService.save(loginObj.getUsername(),loginObj.getPhoneOrEmail(),loginObj.getPassword(),RequestUtil.getIp(request));
        return RestResponse.ok(user,200,"注册成功!");
    }

    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    public RestResponse update(@RequestBody InitObj initObj){
        int genderInt = "男".equals(initObj.getGender()) ?1:0;
        return RestResponse.ok(userService.update(initObj.getId(),initObj.getNickname(),initObj.getAvatar(),genderInt,initObj.getInfo()),200,"更新成功");
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

class LoginObj{
    private Integer id;
    private String token;
    private String username;
    private String password;
private String phoneOrEmail;

    public LoginObj() {
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

    public void setPhoneOrEmail(String phoneOrEmail) {
        this.phoneOrEmail = phoneOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

class InitObj{
    private String nickname;
    private String gender;
   private  String info;
    private Integer id;
   private  String avatar;

    public InitObj() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
