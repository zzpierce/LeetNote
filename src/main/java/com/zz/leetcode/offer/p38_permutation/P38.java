package com.zz.leetcode.offer.p38_permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pierce
 */
public class P38 {
    public static void main(String[] args) {
        String s=  "a";
        Solution solution = new Solution();
        String[] r = solution.permutation(s);
        for (String e : r) {
            System.out.println(e);
        }
    }
}


class Solution {

    private List<String> res;
    int l;

    public String[] permutation(String s) {
        res = new ArrayList<>();
        if (s == null || s.equals("")) return new String[0];
        char[] c = s.toCharArray();
        l = s.length();
        dfs(c, 0);
        String[] r = new String[res.size()];
        return res.toArray(r);
    }

    private void dfs(char[] c, int s) {
        if (s == l - 1) res.add(new String(c));
        int[] t = new int[26];
        for (int i = s; i < c.length; i ++) {
            char x = c[i];
            if (t[x - 'a'] == 0) {
                c[i] = c[s];
                c[s] = x;
                t[x - 'a'] = 1;
                dfs(c, s + 1);
                c[s] = c[i];
                c[i] = x;
            }
        }
    }

}