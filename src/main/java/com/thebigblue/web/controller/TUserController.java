package com.thebigblue.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.thebigblue.web.common.api.JsonResp;
import com.thebigblue.web.domain.TUser;
import com.thebigblue.web.service.TUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表;(t_user)表控制层
 */
@Api(tags = "用户表对象功能接口")
@RestController
@RequestMapping("/tUser")
public class TUserController {
    @Autowired
    private TUserService tUserService;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{userid}")
    public JsonResp<TUser> queryById(String userId) {
        return JsonResp.success(tUserService.queryById(userId));
    }

    /**
     * 分页查询
     *
     * @param tUser       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public JsonResp<PageDTO<TUser>> list(TUser tUser, PageDTO<TUser> pageRequest) {
        Page<TUser> pageResult = tUserService.paginQuery(tUser, pageRequest.getCurrent(), pageRequest.getSize());
        return JsonResp.success(pageResult);
    }

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public JsonResp<TUser> add(TUser tUser) {
        return JsonResp.success(tUserService.insert(tUser));
    }

    /**
     * 更新数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public JsonResp<TUser> edit(TUser tUser) {
        return JsonResp.success(tUserService.update(tUser));
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping()
    public JsonResp<Boolean> deleteById(String userId) {
        return JsonResp.success(tUserService.deleteById(userId));
    }
}