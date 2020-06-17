package com.zz.leetcode.contest.s193.pa_running_sum_of_1d_array;

/**
 * @author pierce
 */
public class P {
}

class Solution {
    public int[] runningSum(int[] nums) {

        int[] r = new int[nums.length];
        int j = 0;
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i --) {
            ans += nums[j];
            r[j] = ans;
            j ++;
        }
        return r;
    }
}