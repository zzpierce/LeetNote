package com.zz.leetcode.offer.p14_I_cut_the_rope;

/**
 * @author pierce
 */
public class P14_I {

    public static void main(String[] args) {
        Solution s = new Solution();
        for (int i = 0; i < 100; i ++) {
            System.out.println(i + " = " + s.cuttingRope(i));
        }

    }

}

class Solution {

    public int cuttingRope(int n) {
        if (n < 4) return n - 1;
        long s = 1L;
        while (n > 4) {
            n -= 3;
            s *= 3;
            if (s > 1000000007) {
                s %= 1000000007;
            }
        }
        s *= n;
        if (s > 1000000007) {
            s %= 1000000007;
        }
        return (int)s;
    }


}
