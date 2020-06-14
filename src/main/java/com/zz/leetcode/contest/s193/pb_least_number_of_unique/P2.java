package com.zz.leetcode.contest.s193.pb_least_number_of_unique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pierce
 */
public class P2 {
}

class Solution {


    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, T> m = new HashMap<>();
        for (int i : arr) {
            if (m.containsKey(i)) {
                m.get(i).c ++;
            } else {
                m.put(i, new T(i, 1));
            }
        }

        List<T> ts = new ArrayList<>();
        ts.addAll(m.values());
        ts.sort((a, b) -> {
            return a.c - b.c;
        });

        int ans = ts.size();
        int p = 0;
        while (k > 0) {
            if (k >= ts.get(p).c) {
                k -= ts.get(p).c;
                ans -= 1;
                p ++;
            }
            else break;
        }
        return ans;
    }

    class T {
        int v;
        int c;
        T(int v, int c){
            this.v =v; this.c = c;
        }
    }
}
