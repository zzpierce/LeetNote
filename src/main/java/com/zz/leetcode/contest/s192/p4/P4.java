package com.zz.leetcode.contest.s192.p4;

/**
 * @author pierce
 */
public class P4 {
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
                        int ma = 0;
                        if (t[i - 1][j][k] != -1) {
                            ma = t[i - 1][j][k] + co;
                        }
                        if (k > 0) {
                            for (int q = 0; q < n; q ++) {
                                if (q != j && t[i - 1][q][k - 1] != -1) {
                                    ma = max(ma, t[i - 1][q][k - 1] + co);
                                }
                            }
                        }
                    }
                }
            }
        }

        int ans = t[m - 1][0][target - 1];
        return ans;
    }

    int max(int x, int y) {
        return x > y ? x : y;
    }
}