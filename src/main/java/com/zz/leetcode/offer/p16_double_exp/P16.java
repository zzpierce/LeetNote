package com.zz.leetcode.offer.p16_double_exp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pierce
 */
public class P16 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.myPow(2, -2147483648));
    }
}

class Solution {

    private List<Double> ds = new ArrayList<>();

    public double myPow(double x, int n) {
        ds.clear();
        long c = 1;
        double b = x;
        long n2 = (long)n;
        long n1 = n > 0 ? n2 : -n2;
        while (c <= n1) {
            ds.add(b);
            b *= b;
            c *= 2;
        }
        double ans = 1.0;
        int p = 0;
        while (n1 > 0) {
            if ((n1 & 1) == 1) {
                ans *= ds.get(p);
            }
            n1 >>= 1;
            p ++;
        }
        return n > 0 ? ans : 1.0 / ans;
    }
}
