package com.thebigblue.web.common.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口响应实体
 **/
@Data
@ApiModel("接口响应实体")
public class JsonResp<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "响应码", position = 1)
    private Integer code = null;
    @ApiModelProperty(value = "响应信息", position = 2)
    private String msg = null;
    @ApiModelProperty(value = "响应数据", position = 3)
    private T data = null;

    private JsonResp() {
    }

    private JsonResp(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResp success() {
        return success(ApiEnum.RSLT_CDE_10000.getName(), null);
    }

    public static <T> JsonResp success(T data) {
        return success(ApiEnum.RSLT_CDE_10000.getName(), data);
    }

    public static JsonResp success(String message) {
        return success(message, null);
    }

    public static <T> JsonResp success(String message, T data) {
        return new JsonResp(ApiEnum.RSLT_CDE_10000.getCode(), message, data);
    }

    public static JsonResp fail() {
        return fail(ApiEnum.RSLT_CDE_9999);
    }

    public static JsonResp fail(String message) {
        return fail(ApiEnum.RSLT_CDE_9999.getCode(), message, null);
    }

    public static JsonResp fail(ApiEnum apiCodeEnum) {
        return fail(apiCodeEnum.getCode(), apiCodeEnum.getName());
    }

    public static <T> JsonResp fail(ApiEnum apiCodeEnum, T data) {
        return fail(apiCodeEnum.getCode(), apiCodeEnum.getName(), data);
    }

    public static JsonResp fail(Integer code, String message) {
        return fail(code, message, null);
    }

    public static <T> JsonResp fail(Integer code, String message, T data) {
        return new JsonResp(code, message, data);
    }
}


