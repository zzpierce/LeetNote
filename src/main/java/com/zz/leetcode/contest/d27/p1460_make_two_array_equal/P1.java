package com.zz.leetcode.contest.d27.p1460_make_two_array_equal;

import java.util.Arrays;

/**
 * @author pierce
 */
public class P1 {
}


class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        if (arr.length != target.length) return false;
        boolean r = true;
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i] != target[i]) return false;
        }
        return true;
    }
}