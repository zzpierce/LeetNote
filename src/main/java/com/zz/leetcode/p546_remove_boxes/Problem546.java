package com.zz.leetcode.p546_remove_boxes;

/**
 * [DP]
 */
public class Problem546 {

    public static void main(String[] args) {
        int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};

        Solution s = new Solution();
        int d = s.removeBoxes(boxes);
        System.out.println(d);
    }

}

class Solution {

    public int removeBoxes(int[] boxes) {
        int[][][] m = new int[100][100][100];
        return dp(0, boxes.length - 1, 0, boxes, m);
    }

    private int dp(int i, int j, int k, int[] boxes, int[][][] m) {
        if (i > j) return 0;
        if (m[i][j][k] > 0) return m[i][j][k];
        int res = (k + 1) * (k + 1) + dp(i + 1, j, 0, boxes, m);

        for (int p = i + 1; p <= j; p ++) {
            if (boxes[i] == boxes[p]) {
                int rp = dp(i + 1, p - 1, 0, boxes, m) + dp(p, j, k + 1, boxes, m);
                res = Math.max(res, rp);
            }
        }
        m[i][j][k] = res;
        return res;
    }
}