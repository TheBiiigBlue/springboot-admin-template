package com.thebigblue.web.utils;


import com.thebigblue.web.common.consts.DatePattern;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateUtil {

    public static String getNowDateStr() {
        return new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN).format(new Date());
    }

    public static String getPreDays(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        Date bDate = calendar.getTime();
        return new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN).format(bDate);
    }

    public static Date getPreDate(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        return calendar.getTime();
    }

    public static int year() {
        return getField(Calendar.YEAR);
    }

    public static int quarter() {
        return (month() - 1) / 3 + 1;
    }

    public static int month() {
        return getField(Calendar.MONTH) + 1;
    }

    public static int getField(int field) {
        return Calendar.getInstance().get(field);
    }


    public static List<Integer> getRange(int start, int endExclude) {
        if (start >= endExclude) return null;
        return IntStream.range(0, endExclude - start).boxed()
                .map(index -> start + index).collect(Collectors.toList());
    }

}
