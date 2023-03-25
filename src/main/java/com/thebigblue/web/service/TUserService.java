package com.thebigblue.web.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thebigblue.web.domain.TUser;

/**
 * 用户表;(t_user)表服务接口
 */
public interface TUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TUser queryById(String userId);

    /**
     * 分页查询
     *
     * @param tUser   筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    Page<TUser> paginQuery(TUser tUser, long current, long size);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser insert(TUser tUser);

    /**
     * 更新数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(String userId);
}
