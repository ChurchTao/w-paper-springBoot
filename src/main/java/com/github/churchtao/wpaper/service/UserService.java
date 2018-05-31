package com.github.churchtao.wpaper.service;


import com.github.churchtao.wpaper.dao.UserRepository;
import com.github.churchtao.wpaper.entity.User;
import com.github.churchtao.wpaper.exception.ServerException;
import com.github.churchtao.wpaper.util.CheckUtil;
import com.github.churchtao.wpaper.util.CodecUtil;
import com.github.churchtao.wpaper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiacheng on 2017/8/17.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userDAO;

    public User save(String loginName, String phoneOrEmail, String password, String ip) {
        User user = new User();
        user.setPhone("暂无");
        boolean isPhone = CheckUtil.checkMobileNumber(phoneOrEmail);
        if (isPhone){
            if (userDAO.getByPhone(phoneOrEmail)!=null){
                logger.info("已经存在该Phone!");
                throw new ServerException(500,"已经存在该Phone!");
            }
            user.setPhone(phoneOrEmail);
        }else if (CheckUtil.checkEmail(phoneOrEmail)){
            if (userDAO.getByEmail(phoneOrEmail)!=null){
                logger.info("已经存在该Email!");
                throw new ServerException(500,"已经存在该Email!");
            }
            user.setEmail(phoneOrEmail);
        }else {
            throw new ServerException(500,"邮箱或手机号填写有误!");
        }
        if (userDAO.getByLoginName(loginName)!=null){
            logger.info("已经存在该登录名了!");
            throw new ServerException(500,"已经存在该登录名了!");
        }
        user.setLoginName(loginName);
        user.setNickname(loginName);
        user.setPassword(CodecUtil.sha1(password));
        user.setInfo("暂无");
        user.setUrl("暂无");
        user.setAvatar("http://p7mnquexm.bkt.clouddn.com/default.jpg");

        user.setStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setLastIp(ip);
        return userDAO.save(user);
    }

    public User loginByToken(int id,String token){
        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()){
            if (token.equals(user.get().getToken())){
                return user.get();
            }
        }
        throw new ServerException(500,"自动登录凭证已过期，请通过账号密码登录~");
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password,String ip) {
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            throw new ServerException(500,"用户名和密码不能为空~");
        }
        boolean isPhone = CheckUtil.checkMobileNumber(username);
        User user;
        if (isPhone){
            user = userDAO.getByPhone(username);
            if(user==null){
                throw new ServerException(500,"不存在该手机号~");
            }
            if (this.checkLogin(user.getPassword(),password)){
                updateUser(user,ip);
                return user;
            }
        }
        if (CheckUtil.checkEmail(username)){
            user = userDAO.getByEmail(username);
            if(user==null){
                throw new ServerException(500,"不存在该邮箱~");
            }
            if (this.checkLogin(user.getPassword(),password)){
                updateUser(user,ip);
                return user;
            }
        }else {
            user = userDAO.getByLoginName(username);
            if(user==null){
                throw new ServerException(500,"不存在该用户~");
            }
            if (this.checkLogin(user.getPassword(),password)){
                updateUser(user,ip);
                user.setPassword("");
                return user;
            }
        }
        throw new ServerException(500,"用户名或密码错误~");
    }

    private void updateUser(User user,String ip){
        user.setLastIp(ip);
        user.setLastTime(new Date());
        userDAO.save(user);
    }

    public User update(int uid,String nickname,String avatar,int gender,String info){
        User user = userDAO.findById(uid).get();
        user.setNickname(nickname);
        user.setAvatar(avatar);
        user.setGender(gender);
        user.setInfo(info);
        userDAO.save(user);
        throw new ServerException(500,"更新出错");
    }

    public User loginRemember(int userId,String token){
        User user =userDAO.findById(userId).get();
        user.setToken(token);
        return userDAO.save(user);
    }

    public User countLike (int userId,int num){
        User user = userDAO.findById(userId).get();
        user.setLikeNum(user.getLikeNum()+num);
        return userDAO.save(user);
    }

    private boolean checkLogin(String password1, String password){
        password= CodecUtil.sha1(password);
        return password1.equals(password);
    }
}
