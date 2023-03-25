package com.thebigblue.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.thebigblue.web.domain.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * 用户表;(t_user)表数据库访问层
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
    /**
     * 分页查询指定行数据
     *
     * @param page    分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<TUser> selectByPage(IPage<TUser> page, @Param(Constants.WRAPPER) Wrapper<TUser> wrapper);

    /**
     * 批量插入（mysql）
     *
     * @param entityList
     * @return
     */
    Integer insertBatchSomeColumn(Collection<TUser> entityList);
}
