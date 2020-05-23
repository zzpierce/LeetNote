package com.zz.leetcode.offer.p11_rotate_array_minimum;

/**
 * @author pierce
 */
public class P11 {

    public static void main(String[] args) {
        int[] n = new int[]{2,2,2,0,2,2};
        Solution s = new Solution();
        System.out.println(s.minArray(n));
    }
}

class Solution {
    public int minArray(int[] numbers) {
        if (numbers[0] < numbers[numbers.length - 1]) {
            return numbers[0];
        }
        return bs(0, numbers.length - 1, numbers);
    }

    private int bs(int s, int e, int[] n) {
        if (s == e) return n[s];
        if (s + 1 == e) {
            return n[s] > n[e] ? n[e] : n[s];
        }
        int h = (s + e) / 2;
        if (n[h] > n[s]) {
            return bs(h, e, n);
        } else if (n[h] < n[s]) {
            return bs(s, h, n);
        } else {
            return mi(bs(h, e, n), bs(s, h, n));
        }
    }

    private int mi(int a, int b) {
        return a < b ? a : b;
    }
}