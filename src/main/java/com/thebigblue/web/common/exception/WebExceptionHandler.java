package com.thebigblue.web.common.exception;

import com.thebigblue.web.common.api.ApiEnum;
import com.thebigblue.web.common.api.JsonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 统一异常处理类
 */
@ApiIgnore
@RestControllerAdvice
public class WebExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(value = SelfException.class)
    public JsonResp handlerException(SelfException ex) {
        LOGGER.error("自定义异常：", ex);
        return JsonResp.fail(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public JsonResp handleException(Exception ex) {
        LOGGER.error("系统异常：", ex);
        if (ex instanceof MissingRequestValueException
                || ex instanceof IllegalArgumentException
                || ex instanceof TypeMismatchException) {
            return JsonResp.fail(ApiEnum.RSLT_CDE_5001);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            return JsonResp.fail("405：请求方式不支持");
        } else if (ex instanceof NoHandlerFoundException) {
            return JsonResp.fail("404：没有找到访问资源");
        } else {
            return JsonResp.fail("500：请求异常，请联系数据部");
        }
    }
}
