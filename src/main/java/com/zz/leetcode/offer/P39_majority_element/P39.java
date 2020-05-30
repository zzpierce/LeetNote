package com.zz.leetcode.offer.P39_majority_element;

/**
 * @author pierce
 */
public class P39 {
}

class Solution {
    public int majorityElement(int[] nums) {
        return c(nums, 0);
    }

    private int c(int[] nums, int s) {
        int r = 0;
        int ma = nums[s];
        for (int i = s; i < nums.length; i ++) {
            if (nums[i] == ma) {
                r ++;
            } else {
                r --;
                if (r == 0) return c(nums, i + 1);
            }
        }
        return ma;
    }
}
