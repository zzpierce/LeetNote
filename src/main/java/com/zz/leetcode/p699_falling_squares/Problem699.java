package com.zz.leetcode.p699_falling_squares;

import java.util.ArrayList;
import java.util.List;

public class Problem699 {

    public static void main(String[] args) {
        int[][] ps = {{9,6},{2,2},{2,6}};
        System.out.println(new Solution().fallingSquares(ps));
    }

}


class Solution {


    public List<Integer> fallingSquares(int[][] positions) {

        List<Integer> height = new ArrayList<>();
        List<Block> blockList = new ArrayList<>();

        int globalHeight = 0;

        for (int[] p : positions) {
            int topHeight = p[1];
            for (Block b : blockList) {
                if ((p[0] < b.right && p[0] + p[1] > b.left)) {
                    topHeight = Math.max(topHeight, b.top + p[1]);
                }
            }
            globalHeight = Math.max(globalHeight, topHeight);
            blockList.add(new Block(p[0], p[0] + p[1], topHeight));
            height.add(globalHeight);
        }

        return height;
    }

    class Block {

        int left;
        int right;
        int top;

        Block(int l, int r, int t) {
            left = l; right = r; top = t;
        }
    }
}