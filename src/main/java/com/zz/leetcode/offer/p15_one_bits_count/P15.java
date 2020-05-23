package com.zz.leetcode.offer.p15_one_bits_count;

/**
 * @author pierce
 */
public class P15 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.hammingWeight(-1));

    }

}

class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int c = 0;
        if (n < 0) {
            n ^= (1 << 31);
            c ++;
        }
        while (n != 0) {
            if ((n & 1) == 1) c ++;
            n >>= 1;
        }
        return c;
    }
}
