package com.github.churchtao.wpaper.handel;

import com.github.churchtao.wpaper.constant.ServerEnum;
import com.github.churchtao.wpaper.exception.ServerException;
import com.github.churchtao.wpaper.util.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author taojiacheng
 * @create 2018-05-29 15:59
 **/
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse handle(Exception e){
        if (e instanceof ServerException){
            ServerException serverException = (ServerException) e;
            return RestResponse.fail(serverException.getCode(),e.getMessage());
        }else{
            logger.error("系统异常：{}",e);
            return RestResponse.fail(ServerEnum.UNKNOW_ERROR.getCode(),ServerEnum.UNKNOW_ERROR.getMsg());
        }
    }
}
