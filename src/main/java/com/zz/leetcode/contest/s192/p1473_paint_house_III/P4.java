package com.zz.leetcode.contest.s192.p1473_paint_house_III;

import com.zz.util.ReadUtil;

/**
 * @author pierce
 */
public class P4 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] h = ReadUtil.readArray("[2,3,0]");
        int[][] c = ReadUtil.read(" [[5,2,3],[3,4,1],[1,2,1]]");

        int minCost = s.minCost(h, c, 3, 3, 3);
        System.out.println(minCost);
    }

}


class Solution {

    int[][][] t;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        t = new int[m][n][m];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                for (int k = 0; k < m; k ++) t[i][j][k] = -1;
            }
        }

        for (int j = 0; j < n; j ++) {
            if (houses[0] == 0) {
                t[0][j][0] = cost[0][j];
            } else if (j + 1 == houses[0]) {
                t[0][j][0] = 0;
            } else {
                t[0][j][0] = -1;
            }
        }

        for (int i = 1; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                for (int k = 0; k <= i; k ++) {
                    if (k >= target) break;
                    int co = 0;
                    if (houses[i] == 0) co = cost[i][j];
                    if (houses[i] == 0 || houses[i] == j + 1) {
                        int ma = -1;
                        if (t[i - 1][j][k] != -1) {
                            ma = t[i - 1][j][k] + co;
                        }
                        if (k > 0) {
                            for (int q = 0; q < n; q ++) {
                                if (q != j && t[i - 1][q][k - 1] != -1) {
                                    ma = mi(ma, t[i - 1][q][k - 1] + co);
                                }
                            }
                        }
                        t[i][j][k] = ma;
                    }
                }
            }
        }

        int ans = -1;
        for (int j = 0; j < n; j ++) {
            ans = mi(ans, t[m - 1][j][target - 1]);
        }
        return ans;
    }

    int mi(int x, int y) {
        if (x < 0) return y;
        if (y < 0) return x;
        return x < y ? x : y;
    }
}