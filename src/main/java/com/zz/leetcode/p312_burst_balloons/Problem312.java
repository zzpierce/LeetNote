package com.zz.leetcode.p312_burst_balloons;

/**
 * @author pierce
 */
public class Problem312 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3,1,5,8};
        int ans = s.maxCoins(nums);
        System.out.println(ans);
    }
}

class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length + 2;
        int[] n = new int[len];
        n[0] = n[len - 1] = 1;
        for (int i = 0; i < nums.length; i ++) {
            n[i + 1] = nums[i];
        }

        int[][] dp = new int[len][len];
        for (int g = 2; g < len; g ++) {
            for (int left = 0; left < len - g; left ++) {
                int right = left + g;
                for (int i = left + 1; i < right; i ++) {
                    dp[left][right] = max(dp[left][right], n[left] * n[i] * n[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][len - 1];
    }

    private int max(int a, int b) {
        if (a > b) return a;
        return b;
    }
}