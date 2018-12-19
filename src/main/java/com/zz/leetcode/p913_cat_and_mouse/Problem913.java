package com.zz.leetcode.p913_cat_and_mouse;

import com.zz.util.ReadUtil;

import java.util.*;

/**
 * @author pierce
 */
public class Problem913 {

    public static void main(String[] args) {
        String json = "[[6],[4],[9],[5],[1,5],[3,4,6],[0,5,10],[8,9,10],[7],[2,7],[6,7]]";
        //String json = "[[4],[3],[5],[4],[0,3,6],[6],[4]]";
        int[][] g = ReadUtil.read(json);
        Solution s = new Solution();
        System.out.println(s.catMouseGame(g));
    }

}

class Solution {

    public int catMouseGame(int[][] graph) {
        int size = graph.length;
        int[][] dp = new int[size][size];
        for (int i = 0; i < size; i ++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < size; i ++) {
            dp[0][i] = 1;
            dp[i][i] = 2;
        }
        int s = dp(1, 2, graph, dp);

        return s;
    }

    private int dp(int mouse, int cat, int[][] g, int[][] dp) {
        if (dp[mouse][cat] != -1) return dp[mouse][cat];
        dp[mouse][cat] = 0;
        int[] mouseTo = g[mouse];
        int[] catTo = g[cat];
        int mouseDefault = 2;
        for (int mouseNext : mouseTo) {
            if (mouseNext == cat) continue; //run from cat
            int catDefault = 1;
            for (int catNext : catTo) {
                if (catNext == 0) continue;
                int result = dp(mouseNext, catNext, g, dp);
                if (result == 2) {
                    catDefault = 2;
                    break;
                }
                if (result == 0) {
                    catDefault = 0;
                }
            }
            if (catDefault == 1) {
                mouseDefault = 1;
                break;
            }
            if (catDefault == 0) {
                mouseDefault = 0;
            }
        }
        dp[mouse][cat] = mouseDefault;
        return mouseDefault;
    }
}