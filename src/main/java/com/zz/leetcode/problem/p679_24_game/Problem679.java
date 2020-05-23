package com.zz.leetcode.problem.p679_24_game;

public class Problem679 {

    public static void main(String[] args) {

        Solution s = new Solution();
        int[] nums = {4, 1, 8, 7};
        boolean b = s.judgePoint24(nums);

        System.out.println(b);
    }

}

class Solution {


    public boolean judgePoint24(int[] nums) {

        double[] n = new double[4];
        for (int i = 0; i < nums.length; i ++) {
            n[i] = (double)nums[i];
        }


        return dfs(n);

    }

    public boolean dfs(double[] n) {

        if (n.length == 1) {
            if (isZero(n[0] - 24.0)) {

                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < n.length; i ++) {
            for (int j = 0; j < n.length; j ++) {

                if (i != j) {
                    double[] sub = new double[n.length - 1];
                    int q = 0;
                    for (int k = 0; k < n.length; k++) {
                        if (k != i && k != j) {
                            sub[q++] = n[k];
                        }
                    }


                    sub[sub.length - 1] = n[i] + n[j];
                    if (dfs(sub)) {
                        return true;
                    }
                    sub[sub.length - 1] = n[i] - n[j];
                    if (dfs(sub)) {
                        return true;
                    }

                    sub[sub.length - 1] = n[i] * n[j];
                    if (dfs(sub)) {
                        return true;
                    }

                    if (!isZero(n[j])) {

                        sub[sub.length - 1] = n[i] / n[j];
                        if (dfs(sub)) {
                            return true;
                        }
                    }
                }

            }
        }

        return false;
    }

    private boolean isZero(double d) {
        if (d - 0.0 > 0.001 || d - 0.0 < -0.001) {
            return false;
        }
        return true;
    }

}

