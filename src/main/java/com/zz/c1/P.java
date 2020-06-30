package com.zz.c1;

/**
 * @author pierce
 */
public class P {
}

class Solution {
    public int xorOperation(int n, int start) {
        int[] r = new int[n];
        for (int i = 0; i < n;i  ++) {
            r[i] = start + 2 * i;
        }
        int ans = r[0];
        for (int i = 1; i < n; i ++) {
            ans = ans ^ r[i];
        }
        return ans;
    }
}