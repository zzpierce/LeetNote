package com.zz.leetcode.offer.p04_find_in_two_di_array;

/**
 * @author pierce
 */
public class P04 {
}

class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j ++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
