package com.thebigblue.web.common.api;

/**
 * 请求响应码
 **/
public enum ApiEnum {

    /***
     * 请求成功
     */
    RSLT_CDE_10000("请求成功", 10000),

    /***
     * 请求参数错误
     */
    RSLT_CDE_5001("请求参数错误", 5001),

    /***
     * 其他错误
     */
    RSLT_CDE_9999("其他错误", 9999);

    private String name;
    private Integer code;


    ApiEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    void setCode(Integer code) {
        this.code = code;
    }
}
