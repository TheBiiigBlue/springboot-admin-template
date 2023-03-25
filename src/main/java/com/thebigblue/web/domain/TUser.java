package com.thebigblue.web.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表;
 */
@ApiModel(value = "用户表")
@TableName("t_user")
@Data
public class TUser implements Serializable, Cloneable {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableId
    private String userId;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String gender;
    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private String birthday;
    /**
     * 生日月份
     */
    @ApiModelProperty(value = "生日月份")
    private Integer birthMonth;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**
     * 加入时间
     */
    @ApiModelProperty(value = "加入时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}