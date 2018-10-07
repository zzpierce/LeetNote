package com.zz.leetcode.p97_interleaving_string;

import java.util.HashMap;
import java.util.Map;

public class Problem97 {

    public static void main(String[] args) {
        Solution s = new Solution();
        boolean b = s.isInterleave("a", "b", "a");
        System.out.println(b);
    }

}


class Solution {

    private String s1;
    private String s2;
    private String s3;

    private Map<String, Boolean> map;

    public boolean isInterleave(String s1, String s2, String s3) {
        this.map = new HashMap<>();
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        boolean r = t1(0, 0, 0);
        return r;
    }

    private boolean t1(int p1, int p2, int p3) {
        if (p3 == s3.length()) {
            return true;
        }
        String key = key(p1, p2, p3);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (p1 < s1.length() && s1.charAt(p1) == s3.charAt(p3)) {
            boolean r1 = t1(p1 + 1, p2, p3 + 1);
            if (r1) {
                map.put(key(p1 + 1, p2, p3 + 1), true);
                return true;
            }
        }
        if (p2 < s2.length() && s2.charAt(p2) == s3.charAt(p3)) {
            boolean r2 = t1(p1, p2 + 1, p3 + 1);
            if (r2) {
                map.put(key(p1, p2 + 1, p3 + 1), true);
                return true;
            }
        }
        map.put(key, false);
        return false;
    }

    private String key(int i, int j, int k) {
        return i + "-" + j + "-" + k;
    }

}