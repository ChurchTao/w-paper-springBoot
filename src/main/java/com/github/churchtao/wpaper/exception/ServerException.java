package com.github.churchtao.wpaper.exception;

/**
 * @author taojiacheng
 * @create 2018-05-29 16:02
 **/
public class ServerException extends RuntimeException {

    private Integer code;

    public ServerException(Integer code,String message) {
        super(message);
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
