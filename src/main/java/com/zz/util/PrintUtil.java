package com.zz.util;

import java.util.Map;

/**
 * @author pierce
 */
public class PrintUtil {

    public static void printMap(Map map) {
        System.out.println("{");
        for (Object key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
        System.out.println("}");
    }

}
