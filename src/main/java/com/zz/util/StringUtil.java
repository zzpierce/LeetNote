package com.zz.util;

public class StringUtil {

    public static String reverse(String s) {
        StringBuilder builder = new StringBuilder();
        char[] c = s.toCharArray();
        for (int i = c.length - 1; i >= 0; i --) {
            builder.append(c[i]);
        }
        return builder.toString();
    }

}
