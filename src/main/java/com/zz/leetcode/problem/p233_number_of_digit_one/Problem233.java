package com.zz.leetcode.problem.p233_number_of_digit_one;

/**
 * @author pierce
 */
public class Problem233 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countDigitOne(int n) {
        long nc = n;
        long ans = 0;
        long dual = 10;
        while (nc > 0) {
            long time = n / dual;
            ans += time * dual / 10;
            long left = n % dual;
            if (left >= dual / 10) {
                long tp = dual / 5 - 1;
                long nLeft = left > tp ? tp : left;
                ans += nLeft + 1 - dual / 10;
            }
            dual *= 10;
            nc /= 10;
        }

        return (int)ans;
    }
}
