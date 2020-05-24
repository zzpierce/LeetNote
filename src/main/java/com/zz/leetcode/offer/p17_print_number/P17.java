package com.zz.leetcode.offer.p17_print_number;

/**
 * @author pierce
 */
public class P17 {
}

class Solution {
    public int[] printNumbers(int n) {
        int m = 1;
        while (n -- > 0) m *= 10;
        int[] r = new int[m];
        for (int i = 1; i < m; i ++) r[i] = i;
        return r;
    }
}
