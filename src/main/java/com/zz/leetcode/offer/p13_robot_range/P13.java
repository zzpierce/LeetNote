package com.zz.leetcode.offer.p13_robot_range;

/**
 * @author pierce
 */
public class P13 {
    public static void main(String[] args) {
        System.out.println(new Solution().movingCount(4,6,15));
    }
}

class Solution {

    private int[][] r;
    private int ans;

    public int movingCount(int m, int n, int k) {
        r = new int[m][n];
        s(0, 0, k);
        return ans;
    }

    private void s(int x, int y, int k) {
        if (x >= 0 && y >= 0 && x < r.length && y < r[0].length) {
            if (r[x][y] == 0 && c(x) + c(y) <= k) {
                //System.out.println(c(x) + "," + c(y) + "," + c(k));
                r[x][y] = 1;
                ans ++;
                s(x - 1, y, k);
                s(x + 1, y, k);
                s(x, y - 1, k);
                s(x, y + 1, k);
            }
        }
    }

    private int c(int v) {
        return v / 10 + v % 10;
    }
}
