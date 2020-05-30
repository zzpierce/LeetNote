package com.zz.leetcode.offer.p40_least_k_number;

import java.util.Arrays;

/**
 * @author pierce
 */
public class P40 {
}

class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) return new int[0];
        Arrays.sort(arr);
        int[] r = new int[k];
        for (int i = 0; i < k; i ++) {
            r[i] = arr[i];
        }
        return r;
    }
}
