package com.github.churchtao.wpaper.constant;

/**
 * Created by jiacheng on 2017/8/22.
 */
public enum LogAction {
    LOGIN("登录"),
    AUTO_LOGIN("自动登录"),
    UP_PWD("修改密码"),
    UP_INFO("修改个人信息"),
    DEL_ATTACH("删除附件"),
    DEL_ARTICLE("删除文章"),
    DEL_PAGE("删除页面"),
    SYS_BACKUP("系统备份"),
    SYS_SETTING("保存系统设置"),
    THEME_SETTING("主题设置"),
    INIT_SITE("初始化站点"),
    RELOAD_SYS("重启系统"),
    CLEAR_CACHE("清除缓存");

    private String action ;

    private LogAction( String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return this.action;
    }
}
