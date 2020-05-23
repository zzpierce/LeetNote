package com.zz.leetcode.problem.p37_sudoku_solver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pierce
 */
public class Problem37 {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] b = new char[9][];
        b[0] = new char[]{'5','3','.','.','7','.','.','.','.'};
        b[1] = new char[]{'6','.','.','1','9','5','.','.','.'};
        b[2] = new char[]{'.','9','8','.','.','.','.','6','.'};
        b[3] = new char[]{'8','.','.','.','6','.','.','.','3'};
        b[4] = new char[]{'4','.','.','8','.','3','.','.','1'};
        b[5] = new char[]{'7','.','.','.','2','.','.','.','6'};
        b[6] = new char[]{'.','6','.','.','.','.','2','8','.'};
        b[7] = new char[]{'.','.','.','4','1','9','.','.','5'};
        b[8] = new char[]{'.','.','.','.','8','.','.','7','9'};

        s.solveSudoku(b);

        System.out.println(b[0][2]);
    }
}

class Solution {

    private boolean[] valid = new boolean[9];
    private boolean f = false;

    public void solveSudoku(char[][] board) {
        dfs(0, 0, board);

        for (char[] row : board) {
            for (char c : row) {
                System.out.print((c - '0') + " ");
            }
            System.out.println("");
        }
    }

    private void dfs(int i, int j, char[][] board) {
//        System.out.println(i + "-" + j);
//        if (j == 2 && i == 7) {
//            System.out.println("j");
//        }
        if (j == 9) {
            f = true;
            return;
        }
        char c = board[i][j];
        if (c == '.') {
            List<Integer> v = check(i, j, board);
            for (int k : v) {
                board[i][j] = (char)(k + '0');
                if (i == 8) {
                    dfs(0, j + 1, board);
                } else {
                    dfs(i + 1, j, board);
                }
                if (f) return;
                board[i][j] = '.';
            }
        } else {
            if (i == 8) {
                dfs(0, j + 1, board);
            } else {
                dfs(i + 1, j, board);
            }
        }

    }

    private List<Integer> check(int i, int j, char[][] board) {
        for (int k = 0; k < 9; k ++) {
            valid[k] = true;
        }
        //row
        for (int k = 0; k < 9; k ++) {
            char c = board[k][j];
            if (c != '.') {
                valid[c - '1'] = false;
            }
            c = board[i][k];
            if (c != '.') {
                valid[c - '1'] = false;
            }
        }

        //section
        int r1 = i - i % 3;
        int c1 = j - j % 3;
        for (int k = r1; k < r1 + 3; k ++) {
            for (int l = c1; l < c1 + 3; l ++) {
                char c = board[k][l];
                if (c != '.') {
                    valid[c - '1'] = false;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int k = 0; k < 9; k ++) {
            if (valid[k]) ans.add(k + 1);
        }
        return ans;
    }


}
