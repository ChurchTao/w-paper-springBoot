package com.github.churchtao.wpaper.constant;

/**
 * @author 78663
 */

public enum ServerEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(200,"成功")
    ;

    private Integer code;
    private String msg;

    ServerEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
