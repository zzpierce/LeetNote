package com.zz.leetcode.contest.s191.p1464_maximum_product;

/**
 * @author pierce
 */
public class P1 {
}

class Solution {
    public int maxProduct(int[] nums) {
        int m = 0;
        int mi = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] > m) {
                m = nums[i]; mi = i;
            }
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (i != mi) {
                int j = nums[i] - 1;
                if ((m - 1) * j > ans) ans = (m - 1) * j;
            }
        }
        return ans;
    }
}