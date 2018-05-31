package com.github.churchtao.wpaper.constant;

public interface BaseConst {

    public static String LOGIN_SESSION_KEY = "login_user";
    /**
     * 文章最多可以输入的文字数
     */
    public static final int MAX_TEXT_COUNT = 200000;

    /**
     * 文章标题最多可以输入的文字个数
     */
    public static final int MAX_TITLE_COUNT = 200;

    /**
     * 点击次数超过多少更新到数据库
     */
    public static final int HIT_EXCEED = 10;

    /**
     * 上传文件最大20M
     */
    public static Integer MAX_FILE_SIZE = 204800;

}
