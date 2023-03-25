package com.thebigblue.web.utils;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.google.common.base.CaseFormat;
import com.thebigblue.web.domain.TUser;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据传入的字段 获取 库表中的字段名称
 */
public class PropExtraUtil {

    public static void main(String[] args) {
        String[] colNames = PropExtraUtil.getColNames(TUser::getUserId,
                TUser::getUserName, TUser::getBirthday);
        Arrays.stream(colNames).forEach(System.out::println);

        System.out.println();
        Arrays.stream(colNames).map(col -> underscore2Camel(col)).forEach(System.out::println);
        System.out.println();

        String[] colNamesNoFormat = PropExtraUtil.getColNamesNoFormat(TUser::getUserId,
                TUser::getUserName, TUser::getBirthday);
        Arrays.stream(colNamesNoFormat).forEach(System.out::println);
    }

    /**
     * 将 lambda 字段 转为 数据库表中的下划线格式
     *
     * @param columns
     * @param <T>
     * @return
     */
    public static <T> String[] getColNames(SFunction<T, ?>... columns) {
        List<String> colList = Arrays.asList(columns).stream().map(i -> {
            //将lambda属性转为字符串属性
            LambdaMeta meta = LambdaUtils.extract(i);
            String fieldName = PropertyNamer.methodToProperty(meta.getImplMethodName());
            //将字符串属性 转为 库表中的下划线形式
            return camel2Underscore(fieldName);
        }).collect(Collectors.toList());
        return colList.toArray(new String[colList.size()]);
    }

    public static <T> String getColName(SFunction<T, ?> column) {
        return getColNames(column)[0];
    }

    /**
     * 获取lambda字段，驼峰式
     *
     * @param columns
     * @param <T>
     * @return
     */
    public static <T> String[] getColNamesNoFormat(SFunction<T, ?>... columns) {
        List<String> colList = Arrays.asList(columns).stream().map(i -> {
            //将lambda属性转为字符串属性
            LambdaMeta meta = LambdaUtils.extract(i);
            return PropertyNamer.methodToProperty(meta.getImplMethodName());
        }).collect(Collectors.toList());
        return colList.toArray(new String[colList.size()]);
    }

    public static <T> String getColNameNoFormat(SFunction<T, ?> column) {
        return getColNamesNoFormat(column)[0];
    }

    /**
     * 驼峰转下划线
     *
     * @param camelCol
     * @return
     */
    public static String camel2Underscore(String camelCol) {
        if (IsNullUtil.isEmpty(camelCol)) return null;
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camelCol);
    }

    /**
     * 下划线转驼峰
     *
     * @param underscoreCol
     * @return
     */
    public static String underscore2Camel(String underscoreCol) {
        if (IsNullUtil.isEmpty(underscoreCol)) return null;
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, underscoreCol);
    }
}
