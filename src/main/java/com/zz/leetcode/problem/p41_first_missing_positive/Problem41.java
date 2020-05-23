package com.zz.leetcode.problem.p41_first_missing_positive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 *
 */
public class Problem41 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] n = new int[]{1, 2, 0};
        int[] n1 = new int[]{1, 3, 4};
        int[] n2 = new int[]{7,8,9,11,12};
        int[] n3 = new int[]{2147483647};
        int[] n4 = new int[]{0,2,2,1,1};
        System.out.println(s.firstMissingPositive(n));
        System.out.println(s.firstMissingPositive(n1));
        System.out.println(s.firstMissingPositive(n2));
        System.out.println(s.firstMissingPositive(n3));
        System.out.println(s.firstMissingPositive(n4));
    }

}

/**
 * [7.0]
 * 思路分析
 * 1. HashTable对相邻元素做合并操作
 * 2. 符合条件的数字必定是第一个不满足 i = index + 1 的数字
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        Map<Integer, Integer> stb = new HashMap<>();
        Map<Integer, Integer> bts = new HashMap<>();
        Set<Integer> st = new HashSet<>();
        int sa = 1;
        for (int i : nums) {
            if (st.contains(i)) {
                continue;
            }
            st.add(i);
            if (i > 0 && i < Integer.MAX_VALUE) {
                if (bts.containsKey(i - 1) && stb.containsKey(i + 1)) {
                    int s = bts.get(i - 1);
                    int b = stb.get(i + 1);
                    bts.remove(i - 1);
                    bts.put(b, s);
                    stb.remove(i + 1);
                    stb.put(s, b);
                    if (b + 1 < sa || i == sa) {
                        sa = b + 1;
                    }
                } else if (bts.containsKey(i - 1)) {
                    int s = bts.get(i - 1);
                    bts.remove(i - 1);
                    bts.put(i, s);
                    stb.put(s, i);
                    if (i + 1 < sa || i == sa) {
                        sa = i + 1;
                    }
                } else if (stb.containsKey(i + 1)) {
                    int b = stb.get(i + 1);
                    stb.remove(i + 1);
                    stb.put(i, b);
                    bts.put(b, i);
                    if (b + 1 < sa || i == sa) {
                        sa = b + 1;
                    }
                } else {
                    stb.put(i, i);
                    bts.put(i, i);
                    if (i + 1 < sa || i == sa) {
                        sa = i + 1;
                    }
                }
            }
        }
        return sa;
    }
}