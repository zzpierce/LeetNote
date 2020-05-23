package com.zz.leetcode.problem.p466_count_the_repetitions;

/**
 * @author pierce
 */
public class Problem466 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int ans = s.getMaxRepetitions("aaa", 3, "aa", 1);
        System.out.println(ans);
    }
}

class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[] next = new int[l2];
        int[] r = new int[l2];
        //init next

        for (int i = 0; i < l2; i ++) {
            int p = i;
            int round = 0;
            for (int j = 0; j < l1; j ++) {
                if (s1.charAt(j) == s2.charAt(p)) {
                    p ++;
                    if (p >= s2.length()) {
                        round ++;
                        p = 0;
                    }
                }
            }
            next[i] = p;
            r[i] = round;
        }

        int q = 0;
        int t = 0;
        for (int i = 0; i < n1; i ++) {
            t += r[q];
            q = next[q];
        }
        return t / n2;
    }
}