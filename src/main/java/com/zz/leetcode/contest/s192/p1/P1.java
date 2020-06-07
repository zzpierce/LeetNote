package com.zz.leetcode.contest.s192.p1;

/**
 * @author pierce
 */
public class P1 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] r = new int[]{1,2,3,4,5,6};
    }
}

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] r = new int[nums.length];
        int px = 0, qx = 0, py = nums.length - 1, qy = nums.length - 1;
        while (qx < nums.length) {
            r[qx] = nums[px];
            px ++;
            qx += 2;
            r[qy] = nums[py];
            py --;
            qy -= 2;
        }
        return r;
    }
}
