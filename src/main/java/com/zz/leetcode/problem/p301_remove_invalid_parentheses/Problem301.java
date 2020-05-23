package com.zz.leetcode.problem.p301_remove_invalid_parentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 */
public class Problem301 {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.removeInvalidParentheses(")a(");
    }
}

/**
 * 思路分析
 * 一个合理的串s，满足以下条件
 * 1. '('和')'数量相等
 * 2. 对于任意index i，左侧子串'('的数量大于等于')'的数量，右侧子串')'的数量大于等于'('的数量
 *
 * 实现方案
 * 1. 遍历字符串，如果')'数量大于'('，说明需要删除一个')'，此时选择删除每一个')'连续串的第一个，并进行DFS
 * 结束后可以保证从左向右'('数量始终大于')'
 * 2. 对1的结果反向遍历，用同样的方式删除多余的'('，结果即为正确串
 */
class Solution {

    private List<String> valid;

    public List<String> removeInvalidParentheses(String s) {
        valid = new ArrayList<>();
        dfs(s, ')', 0);
        if (valid.isEmpty()) {
            valid.add("");
        }
        System.out.println(valid);
        return valid;
    }

    private void dfs(String s, char c, int last) {
        int over = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                over += (s.charAt(i) == c) ? 1 : -1;
            }
            if (over > 0) {
                //只能从last - i中间选一个c删除
                for (int j = last; j <= i; j ++) {
                    if (s.charAt(j) == c && (j == 0 || s.charAt(j - 1) != c)) {
                        String s1 = s.substring(0, j) + s.substring(j + 1);
                        dfs(s1, c, j);
                    }
                }
                return;
            }
            if (i == s.length() - 1) {
                if (c == ')') {
                    dfs(reverse(s), '(', 0);
                } else {
                    valid.add(reverse(s));
                }
            }
        }
    }

    public static String reverse(String s) {
        StringBuilder builder = new StringBuilder();
        char[] c = s.toCharArray();
        for (int i = c.length - 1; i >= 0; i --) {
            builder.append(c[i]);
        }
        return builder.toString();
    }
}
