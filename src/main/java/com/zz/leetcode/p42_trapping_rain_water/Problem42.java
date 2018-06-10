package com.zz.leetcode.p42_trapping_rain_water;

/**
 * @author pierce
 */
public class Problem42 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int trap(int[] height) {

        int len = height.length;
        if (len < 3) {
            return 0;
        }

        int top = 0, topIndex = 0, rock = 0, water = 0;
        for (int i = 0; i < len; i ++) {
            rock += height[i];
            if (height[i] > top) {
                top = height[i];
                topIndex = i;
            }
        }

        int currentTop = 0;
        for (int i = 0; i <= topIndex; i ++) {
            if (height[i] > currentTop) {
                currentTop = height[i];
            }
            water += currentTop;
        }

        currentTop = 0;
        for (int i = len - 1; i > topIndex; i --) {
            if (height[i] > currentTop) {
                currentTop = height[i];
            }
            water += currentTop;
        }

        return water - rock;

    }
}