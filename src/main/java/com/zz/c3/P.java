package com.zz.c3;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pierce
 */
public class P {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] r = new int[]{10,20,20};
        System.out.println(JSON.toJSONString(s.avoidFlood(r)));
        r = new int[]{1,2,3,4};
        System.out.println(JSON.toJSONString(s.avoidFlood(r)));
        r = new int[]{1,2,0,0,2,1};
        System.out.println(JSON.toJSONString(s.avoidFlood(r)));
        r = new int[]{69,0,0,0,69};
        System.out.println(JSON.toJSONString(s.avoidFlood(r)));
        r = new int[]{0,1,1};
        System.out.println(JSON.toJSONString(s.avoidFlood(r)));
    }
}

class Solution {
    public int[] avoidFlood(int[] rains) {
        int[] zero = new int[rains.length];
        int[] d = new int[rains.length];
        int zp = -1;
        for (int i = rains.length - 1; i >= 0; i --) {
            d[i] = -1;
            if (rains[i] == 0) {
                zp = i;
                zero[i] = -1;
            }
            else {
                zero[i] = zp;
            }
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < rains.length; i ++) {
            if (rains[i] > 0) {
                int lake = rains[i];
                if (!m.containsKey(lake)) {
                    m.put(lake, i);
                } else {
                    int preIndex = m.get(lake);
                    int zeroIndex = zero[preIndex];
                    if (zeroIndex == -1) return new int[]{};
                    boolean isF = false;
                    for (int j = zeroIndex; j < i; j ++) {
                        if (rains[j] == 0 && d[j] == -1) {
                            d[j] = lake;
                            isF = true;
                            m.put(lake, i);
                            break;
                        }
                    }
                    if (!isF) return new int[]{};
                }
            }
        }
        for (int i = 0; i < rains.length; i ++) {
            if (rains[i] == 0 && d[i] == -1) {
                d[i] = 1;
            }
        }
        return d;
    }
}
