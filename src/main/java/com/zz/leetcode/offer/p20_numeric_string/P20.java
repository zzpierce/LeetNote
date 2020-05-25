package com.zz.leetcode.offer.p20_numeric_string;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"、"-1E-16"及"12e+5.4"都不是。
 */
public class P20 {
    public static void main(String[] args) {
        p("+100", true);
        p("5e2", true);
        p("-123", true);
        p("3.1314", true);
        p("0123", true);
        p("1 ", true);
        p(".1", true);
        p("3.", true);
        p("3.e2", true);
        p("3e+12", true);

        p("12e", false);
        p("1a3.13", false);
        p("1.2.3", false);
        p("+-4" ,false);
        p("-1E-16", false);
        p("12e+5.4", false);
        p(" ", false);
        p(".", false);
        p(".e1", false);
        p("-e23", false);
        p("3e+", false);
    }

    private static void p(String s, boolean b) {
        Solution solution = new Solution();
        System.out.println(solution.isNumber(s) + " - " + b);
    }
}

class Solution {

    private boolean hasE = false;
    private boolean hasDot = false;
    private boolean hasOp = false;

    public boolean isNumber(String s) {
        s = s.trim();
        if (!fullCheck(s)) return false;
        for (int i = 0; i < s.length(); i ++) {
            if (!numCheck(s, i)) return false;
            if (!dotCheck(s, i)) return false;
            if (!opCheck(s, i)) return false;
            if (!eCheck(s, i)) return false;
        }
        return true;
    }

    private boolean fullCheck(String s) {
        if (s.isEmpty()) return false;
        boolean hasDigit = false;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                hasDigit = true;
            }
        }
        if (!hasDigit) return false;
        return true;
    }

    private boolean numCheck(String s, int i) {
        char c = s.charAt(i);
        if (c > '9' || c < '0') {
            if (c != '.' && c != '+' && c != '-' && c != 'e') {
                return false;
            }
        }
        return true;
    }

    private boolean dotCheck(String s, int i) {
        char c = s.charAt(i);
        if (c == '.') {
            if (hasE) return false;
            if (hasDot) return false;
            hasDot = true;
        }
        return true;
    }

    private boolean opCheck(String s, int i) {
        char c = s.charAt(i);
        if (c == '+' || c == '-') {
            if (i == s.length() - 1) return false;
            if (i != 0 && s.charAt(i - 1) != 'e') return false;
            hasOp = true;
        }
        return true;
    }

    private boolean eCheck(String s, int i) {
        char c = s.charAt(i);
        if (c == 'e') {
            if (hasE) return false;
            if (i == 0 || i == s.length() - 1) return false;
            if (s.charAt(i - 1) == '.') {
                if (i == 1) return false;
                else return true;
            }
            if (s.charAt(i - 1) == '+' || s.charAt(i - 1) == '-') {
                return false;
            }
            hasE = true;
        }
        return true;
    }

}
