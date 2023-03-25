package com.thebigblue.web.common.enums;

/**
 * 码表
 **/
public enum CommonCodeEnum {

    ALL_DICT("所有码表", "ALL_DICT");

    private String name;
    private String code;

    CommonCodeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }
}
