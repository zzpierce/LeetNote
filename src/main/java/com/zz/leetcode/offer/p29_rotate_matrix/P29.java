package com.zz.leetcode.offer.p29_rotate_matrix;

public class P29 {
}

class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{};
        }
        int m = matrix.length, n = matrix[0].length;
        int[] ans = new int[m * n];

        int[] il = new int[]{0,1,0,-1};
        int[] jl = new int[]{1,0,-1,0};
        int pl = 0;
        int ip = 0; int jp = 0;
        for (int i = 0; i < m * n; i ++) {
            ans[i] = matrix[ip][jp];
            matrix[ip][jp] = -377;
            int inext = ip + il[pl];
            int jnext = jp + jl[pl];
            if (inext < 0 || jnext < 0 || inext >= m || jnext >= n || matrix[inext][jnext] == -377) {
                pl ++; pl %= 4;
            }
            ip += il[pl];
            jp += jl[pl];
        }
        return ans;
    }
}
