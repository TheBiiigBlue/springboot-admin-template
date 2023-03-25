package com.thebigblue.web.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomUtil {

    /**
     * 包含max 和 0
     *
     * @param max
     * @return
     */
    public static int getRandomNum(int max) {
        return (int) (Math.random() * (max + 1));
    }

    /**
     * 包含max 和 min
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNum(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * 随机获取 0 - 1 的指定位数小数
     *
     * @param len 指定位数
     * @return
     */
    public static double getRandomDouble(int len) {
        return doubleFormat(Math.random(), len);
    }

    /**
     * 格式化 double 位指定小数位
     *
     * @param len 指定位数
     * @return
     */
    public static double doubleFormat(double number, int len) {
        String dotLen = IntStream.range(0, len).boxed().map(index -> "0").collect(Collectors.joining());
        String doubleStr = new DecimalFormat("0." + dotLen).format(number);
        return Double.valueOf(doubleStr);
    }

    /**
     * 获取固定长度整数
     *
     * @param len
     * @return
     */
    public static int getFixLenRandomNum(int len) {
        return (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
    }

    /**
     * 从指定数组中 随机获取 指定数量的元素
     *
     * @param dataList
     * @param number
     * @return
     */
    public static <T> List<T> randomGet(List<T> dataList, Integer number) {
        return new ArrayList(IntStream.range(0, number).boxed()
                .map(index -> dataList.get(getRandomNum(dataList.size() - 1)))
                .collect(Collectors.toSet()));
    }

    /**
     * 获取指定位数uuid，最大不能超过32
     *
     * @return
     */
    public static String getUUID(Integer len) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        if (len == null || len == 32) {
            return uuid;
        }
        return uuid.substring(0, len);
    }
}
