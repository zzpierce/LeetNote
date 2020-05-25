package com.zz.leetcode.offer.p21_odd_before_even;

public class P21 {
}

class Solution {
    public int[] exchange(int[] nums) {
        if (nums.length > 0) {
            int p = 0, q = nums.length - 1;
            while (p < q) {
                if ((nums[p] & 1) == 1) {
                    p ++; continue;
                }
                if ((nums[q] & 1) == 0) {
                    q --; continue;
                }
                int c = nums[p];
                nums[p] = nums[q];
                nums[q] = c;
            }
        }
        return nums;
    }
}
