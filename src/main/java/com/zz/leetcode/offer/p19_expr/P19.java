package com.zz.leetcode.offer.p19_expr;

/**
 * @author pierce
 */
public class P19 {
    public static void main(String[] args) {
        Solution s = new Solution();
        boolean r = s.isMatch("aaa", "ab*a");
        System.out.println(r + " - false");
        boolean r1 = s.isMatch("a", "ab*");
        System.out.println(r1 + " - true");
        System.out.println(s.isMatch("aab", "c*a*b") + " - true");
    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        return sub(s, p, 0, 0);
    }

    private boolean sub(String s, String p, int a, int b) {
        if (a == s.length() && b == p.length()) {
            return true;
        }
        if (b >= p.length()) {
            return false;
        }
        if (a >= s.length()) {
            for (int i = b; i < p.length(); i += 2) {
                if (p.charAt(i) == '*' || i + 1 >= p.length() || p.charAt(i + 1) != '*') return false;
            }
        }
        if (p.charAt(b) == '.') {
            if (b < p.length() - 1 && p.charAt(b + 1) == '*') {
                for (int i = a; i <= s.length(); i ++) {
                    if (sub(s, p, i, b + 2)) return true;
                }
            }
            return sub(s, p, a + 1, b + 1);
        }
        if (p.charAt(b) != '*') {
            if (b < p.length() - 1 && p.charAt(b + 1) == '*') {
                if (sub(s, p, a, b + 2)) return true;
                char cp = p.charAt(b);
                for (int i = a; i < s.length(); i ++) {
                    if (s.charAt(i) == cp) {
                        if (sub(s, p, i + 1, b + 2)) return true;
                    } else {
                        return false;
                    }
                }
            } else if (p.charAt(b) == s.charAt(a)) {
                return sub(s, p, a + 1, b + 1);
            } else return false;
        }
        return false;
    }
}