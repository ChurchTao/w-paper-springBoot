package com.github.churchtao.wpaper.service;



import com.github.churchtao.wpaper.dao.LogsRepository;
import com.github.churchtao.wpaper.entity.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jiacheng on 2017/8/22.
 */
@Service
public class LogService {
    @Autowired
    private LogsRepository logDAO;

    public Logs save(String action, String data, String ip, String nickName, int userId) {
        Logs log = new Logs();
        log.setAction(action);
        log.setData(data);
        log.setActionIp(ip);
        log.setUserName(nickName);
        log.setUserId(userId);
        log.setActionTime(new Date());
        return logDAO.save(log);
    }
}
