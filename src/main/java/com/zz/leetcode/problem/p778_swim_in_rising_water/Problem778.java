package com.zz.leetcode.problem.p778_swim_in_rising_water;

/**
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 *
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
 *
 * Example 1:
 *
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 *
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 *
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 *
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 */
public class Problem778 {

    public static void main(String[] args) {
//        int[][] g = new int[5][];
//        g[0] = new int[]{0,1,2,3,4};
//        g[1] = new int[]{24,23,22,21,5};
//        g[2] = new int[]{12,13,14,15,16};
//        g[3] = new int[]{11,17,18,19,20};
//        g[4] = new int[]{10,9,8,7,6};

        int[][] g = new int[2][];
        g[0] = new int[]{0,2};
        g[1] = new int[]{1,3};


        Solution s = new Solution();
        int r = s.swimInWater(g);
        System.out.println(r);
    }

}

/**
 * [3.0]
 * 简单DFS，水漫金山
 * 可以用二分查找优化时间复杂度
 */
class Solution {

    public int swimInWater(int[][] grid) {
        int side = grid.length;
        int[][] m = new int[side][side];
        m[0][0] = 0;
        int level = 1;
        while (true) {
            flow(grid, m, 0, 0, level ++);
            if (m[side - 1][side - 1] != 0) {
                return m[side - 1][side - 1];
            }
        }
    }

    void flow(int[][] grid, int[][] m, int x, int y, int level) {
        if (x >= 0 && y >= 0 && x < grid.length && y < grid.length && m[x][y] < level && grid[x][y] <= level) {
            m[x][y] = level;
            flow(grid, m, x - 1, y, level);
            flow(grid, m, x, y - 1, level);
            flow(grid, m, x + 1, y, level);
            flow(grid, m, x, y + 1, level);
        }
    }

}