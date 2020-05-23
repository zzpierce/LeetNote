package com.zz.leetcode.problem.p282_expression_add_operators;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 */
public class Problem282 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.addOperators("123", 6));
        System.out.println(s.addOperators("232", 8));
        System.out.println(s.addOperators("105", 5));
        System.out.println(s.addOperators("00", 0));
        System.out.println(s.addOperators("", 5));
        System.out.println(s.addOperators("10009", 9));
        System.out.println(s.addOperators("2147483648", -2147483648));
    }

}


/**
 * [3.0]
 * 思路分析
 * DFS遍历所有的情况，并计算结果
 * 注意事项：
 * 0开头的数字不符合规范，除了0
 * 注意integer边界条件，最好使用long
 */
class Solution {

    private List<String> values;

    public List<String> addOperators(String num, long target) {
        long[] c = new long[num.length()];
        char[] s = new char[num.length()];
        int i = 0;
        for (char ch : num.toCharArray()) {
            c[i ++] = ch - '0';
        }
        values = new ArrayList<>();
        if (num.length() > 0) {
            dfs(c, s, 0, target);
        }
        return values;
    }

    public void dfs(long[] c, char[] s, int op, long target) {
        if (op == c.length - 1) {
            if (eva(c, s) == target) {
                values.add(make(c, s));
            }
        } else {
            if (c[op] != 0 || (op > 0 && s[op - 1] == ' ')) {
                s[op] = ' '; dfs(c, s, op + 1, target);
            }
            s[op] = '+'; dfs(c, s, op + 1, target);
            s[op] = '-'; dfs(c, s, op + 1, target);
            s[op] = '*'; dfs(c, s, op + 1, target);
        }
    }

    private long eva(long[] c1, char[] s1) {
        long[] c = copyInt(c1);
        char[] s = copyChar(s1);

        for (int i = 0; i < c.length; i ++) {
            if (s[i] == ' ') {
                c[i + 1] = c[i] * 10 + c[i + 1];
                s[i] = 'd';
            }
        }
        for (int i = 0; i < c.length; i ++) {
            if (s[i] == '*') {
                for (int j = i + 1; j < c.length; j ++) {
                    if (s[j] != 'd') {
                        c[j] = c[i] * c[j];
                        s[i] = 'd';
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < c.length; i ++) {
            if (s[i] == '+' || s[i] == '-') {
                for (int j = i + 1; j < c.length; j ++) {
                    if (s[j] != 'd') {
                        c[j] = s[i] == '+' ? c[i] + c[j] : c[i] - c[j];
                        s[i] = 'd';
                        break;
                    }
                }
            }
        }
        return c[c.length - 1];
    }

    private String make(long[] c, char[] s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < c.length; i ++) {
            b.append(c[i]);
            if (s[i] != ' ' && i < c.length - 1) {
                b.append(s[i]);
            }
        }
        return b.toString();
    }

    private long[] copyInt(long[] c) {
        long[] c1 = new long[c.length];
        System.arraycopy(c, 0, c1, 0, c1.length);
        return c1;
    }

    private char[] copyChar(char[] c) {
        char[] c1 = new char[c.length];
        System.arraycopy(c, 0, c1, 0, c1.length);
        return c1;
    }

}