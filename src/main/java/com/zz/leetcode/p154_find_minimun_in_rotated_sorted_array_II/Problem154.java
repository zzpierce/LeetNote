package com.zz.leetcode.p154_find_minimun_in_rotated_sorted_array_II;

public class Problem154 {
}


class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            if (i < min) min = i;
        }
        return min;
    }
}
