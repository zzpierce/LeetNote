package com.zz.leetcode.contest.s191.p1465_maximum_area_of_a_cake;

import java.util.Arrays;

/**
 * @author pierce
 */
public class P2 {
    public static void main(String[] args) {
        int[] h = new int[]{3};
        int[] v = new int[]{3};
        Solution s = new Solution();
        int r = s.maxArea(5,4,h,v);
        System.out.println(r);
    }
}

class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int sa = horizontalCuts[0], sb = verticalCuts[0];
        for (int i = 0; i < horizontalCuts.length - 1; i ++) {
            if (horizontalCuts[i + 1] - horizontalCuts[i] > sa) {
                sa = horizontalCuts[i + 1] - horizontalCuts[i];
            }
        }
        if (h - horizontalCuts[horizontalCuts.length - 1] > sa) sa = h - horizontalCuts[horizontalCuts.length - 1];
        for (int j = 0; j < verticalCuts.length - 1; j ++) {
            if (verticalCuts[j + 1] - verticalCuts[j] > sb) {
                sb = verticalCuts[j + 1] - verticalCuts[j];
            }
        }
        if (w - verticalCuts[verticalCuts.length - 1] > sb) sb = w - verticalCuts[verticalCuts.length - 1];
        long a = (long)sa * (long)sb;
        long c = a % 1000000007;
        return (int)c;
    }
}