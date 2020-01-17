package com.zz.leetcode.p273_integer_to_english_words;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * Example 1:
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class Problem273 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.numberToWords(123));
        System.out.println(s.numberToWords(12345));
        System.out.println(s.numberToWords(1234567));
        System.out.println(s.numberToWords(1234567891));
        System.out.println(s.numberToWords(20));
        System.out.println(s.numberToWords(1000010000));
    }

}

/**
 * [4.0]
 * 思路分析
 * 简单的实现题，注意边界条件
 * Thousand出现的条件 (num / 1000) % 1000 > 0
 */
class Solution {

    String[] base = {"Thousand", "Million", "Billion"};
    String[] f1 = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] f2 = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return word(num, 0);
    }

    private String word(int num, int bp) {
        if (num / 1000 > 0) {
            String w = word(num / 1000, bp + 1);
            if ((num / 1000) % 1000 > 0) {
                w = w + " " + base[bp];
            }
            if ((num % 1000) > 0) {
                w = w + " " + t(num % 1000);
            }
            return w;
        } else {
            return t(num);
        }
    }

    private String t(int num) {
        StringBuilder b = new StringBuilder();
        if (num / 100 > 0) {
            b.append(f1[num / 100 - 1]).append(" Hundred");
        }
        int t = num % 100;
        if (t > 0) {
            if (t < 20) {
                b.append(" ").append(f1[t - 1]);
            } else {
                b.append(" ").append(f2[t / 10 - 2]);
                if (t % 10 > 0) {
                    b.append(" ").append(f1[t % 10 - 1]);
                }
            }
        }
        return b.toString().trim();
    }

}