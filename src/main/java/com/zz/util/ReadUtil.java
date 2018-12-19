package com.zz.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * @author pierce
 */
public class ReadUtil {

    public static int[][] read(String json) {
        JSONArray array = JSON.parseArray(json);
        int size = array.size();
        int[][] g = new int[size][];
        int i = 0;
        for (Object a : array) {
            JSONArray ray = (JSONArray) a;
            Integer[] objs = ray.toArray(new Integer[0]);
            int s = objs.length;
            g[i] = new int[s];
            for (int j = 0; j < s; j ++) {
                g[i][j] = objs[j];
            }
            i ++;
        }
        return g;
    }

}
