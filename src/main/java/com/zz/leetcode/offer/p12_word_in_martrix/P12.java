package com.zz.leetcode.offer.p12_word_in_martrix;

/**
 * @author pierce
 */
public class P12 {
    public static void main(String[] args) {
        char[][] b = new char[3][4];
        b[0] = new char[]{'a','b','c','e'};
        b[1] = new char[]{'s','f','c','s'};
        b[2] = new char[]{'a','d','e','e'};

        Solution s = new Solution();
        boolean a = s.exist(b, "ccedd");
        System.out.println(a);
    }
}

class Solution {

    private String word;
    char[][] b;

    public boolean exist(char[][] board, String word) {
        b = board;
        this.word = word;
        for (int i = 0; i < b.length; i ++) {
            for (int j = 0; j < b[0].length; j ++) {
                if (b[i][j] == word.charAt(0)) {
                    boolean r = s(0, i, j);
                    if (r) return true;
                }
            }
        }
        return false;
    }

    private boolean s(int p, int x, int y) {
        if (p == word.length()) return true;
        if (x >= 0 && x < b.length && y >= 0 && y < b[0].length) {
            char c = b[x][y];
            if (c == word.charAt(p)) {
                b[x][y] = ' ';
                boolean r = s(p + 1, x + 1, y) || s(p + 1, x, y + 1) || s(p + 1, x - 1, y) || s(p + 1, x, y - 1);
                b[x][y] = c;
                return r;
            }
        }
        return false;
    }
}
