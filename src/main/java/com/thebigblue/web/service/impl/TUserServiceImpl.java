package com.thebigblue.web.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thebigblue.web.domain.TUser;
import com.thebigblue.web.mapper.TUserMapper;
import com.thebigblue.web.service.TUserService;
import com.thebigblue.web.utils.IsNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表;(t_user)表服务实现类
 */
@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public TUser queryById(String userId) {
        return tUserMapper.selectById(userId);
    }

    /**
     * 分页查询
     *
     * @param tUser   筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    public Page<TUser> paginQuery(TUser tUser, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<TUser> queryWrapper = new LambdaQueryWrapper<>();
        if (IsNullUtil.isNotEmpty(tUser.getUserName())) {
            queryWrapper.eq(TUser::getUserName, tUser.getUserName());
        }
        if (IsNullUtil.isNotEmpty(tUser.getPassword())) {
            queryWrapper.eq(TUser::getPassword, tUser.getPassword());
        }
        if (IsNullUtil.isNotEmpty(tUser.getGender())) {
            queryWrapper.eq(TUser::getGender, tUser.getGender());
        }
        if (IsNullUtil.isNotEmpty(tUser.getBirthday())) {
            queryWrapper.eq(TUser::getBirthday, tUser.getBirthday());
        }
        if (IsNullUtil.isNotEmpty(tUser.getPhone())) {
            queryWrapper.eq(TUser::getPhone, tUser.getPhone());
        }
        //2. 执行分页查询
        Page<TUser> pagin = new Page<>(current, size, true);
        Page<TUser> selectResult = tUserMapper.selectPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    public TUser insert(TUser tUser) {
        tUserMapper.insert(tUser);
        return tUser;
    }

    /**
     * 更新数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    public TUser update(TUser tUser) {
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<TUser> chainWrapper = new LambdaUpdateChainWrapper<TUser>(tUserMapper);
        if (IsNullUtil.isNotEmpty(tUser.getUserName())) {
            chainWrapper.set(TUser::getUserName, tUser.getUserName());
        }
        if (IsNullUtil.isNotEmpty(tUser.getPassword())) {
            chainWrapper.set(TUser::getPassword, tUser.getPassword());
        }
        if (IsNullUtil.isNotEmpty(tUser.getGender())) {
            chainWrapper.set(TUser::getGender, tUser.getGender());
        }
        if (IsNullUtil.isNotEmpty(tUser.getBirthday())) {
            chainWrapper.set(TUser::getBirthday, tUser.getBirthday());
        }
        if (IsNullUtil.isNotEmpty(tUser.getPhone())) {
            chainWrapper.set(TUser::getPhone, tUser.getPhone());
        }
        //2. 设置主键，并更新
        chainWrapper.eq(TUser::getUserId, tUser.getUserId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if (ret) {
            return queryById(tUser.getUserId());
        } else {
            return tUser;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    public boolean deleteById(String userId) {
        int total = tUserMapper.deleteById(userId);
        return total > 0;
    }
}
