package com.zz.leetcode.problem.p664_strange_printer;

public class Problem664 {

    public static void main(String[] args) {
        String s = "aaabbbccc";
        Solution solution = new Solution();
        System.out.println(solution.strangePrinter(s));
    }

}

class Solution {
    public int strangePrinter(String s) {

        int[][] m = new int[100][100];
        return dp(0, s.length() - 1, s, m);

    }

    private int dp(int i, int j, String s, int[][] m) {
        if (i > j) return 0;
        if (m[i][j] > 0) return m[i][j];
        int res = 1 + dp(i + 1, j, s, m);
        for (int q = i + 1; q <= j; q ++) {
            if (s.charAt(i) == s.charAt(q)) {
                int rq = dp(i + 1, q - 1, s, m) + dp(q, j, s, m);
                res = Math.min(res, rq);
            }
        }
        m[i][j] = res;
        return res;
    }
}
