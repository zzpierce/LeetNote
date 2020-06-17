package com.zz.leetcode.contest.d27.p1461_check_if_string_contain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pierce
 */
public class P2 {
    public static void main(String[] args) {
        String s = "00110110";
        Solution solution = new Solution();
        System.out.println(solution.hasAllCodes(s, 2));
        System.out.println(solution.hasAllCodes("00110", 2));
        System.out.println(solution.hasAllCodes("0110", 1));
        System.out.println(solution.hasAllCodes("0000000001011100", 4));
    }
}

class Solution {

    Set<Integer> se = new HashSet<>();

    public boolean hasAllCodes(String s, int k) {
        if (k >= s.length()) return false;
        se.clear();
        for (int i = 0; i <= s.length() - k; i ++) {
            String sub = s.substring(i, i + k);
            int ii = ti(sub);
            se.add(ii);
        }
        if (se.size() == pow(k)) {
            return true;
        }
        return false;
    }

    private int ti(String s) {
        int d = 1;
        int r = 0;
        for (int i = s.length() - 1; i >= 0; i --) {
            char c = s.charAt(i);
            if (c == '1') {
                r += d;
            }
            d *= 2;
        }
        return r;
    }

    private int pow(int p) {
        int i = 1;
        while(p > 0) {
            i *= 2;
            p --;
        }
        return i;
    }
}
