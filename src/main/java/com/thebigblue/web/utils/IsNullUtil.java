package com.thebigblue.web.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class IsNullUtil {

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isAnyEmpty(Object... params) {
        if (isEmpty(params)) return true;
        else {
            long emptyCnt = Arrays.stream(params).map(param -> isEmpty(param)).filter(flag -> flag).count();
            return emptyCnt == 0 ? false : true;
        }
    }

    public static boolean isAllEmpty(Object... params) {
        if (isEmpty(params)) return true;
        else {
            long notEmptyCnt = Arrays.stream(params).map(param -> isNotEmpty(param)).filter(flag -> flag).count();
            return notEmptyCnt > 0 ? false : true;
        }
    }

    public static void main(String[] args) {
        System.out.println(!isAllEmpty("abd", null, List.of()));
    }
}
