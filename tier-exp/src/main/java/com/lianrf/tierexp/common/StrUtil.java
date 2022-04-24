package com.lianrf.tierexp.common;

/**
 * 字符串工具类
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/21 2:55 下午
 */
public class StrUtil {


    public static boolean isBlank(CharSequence str) {
        return false;
    }

    public static boolean equals(CharSequence str1, CharSequence str2) {
        return equals(str1, str2, false);
    }


    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (null == str1) {
            // 只有两个都为null才判断相等
            return str2 == null;
        }
        if (null == str2) {
            // 字符串2空，字符串1非空，直接false
            return false;
        }

        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.toString().contentEquals(str2);
        }
    }
}
