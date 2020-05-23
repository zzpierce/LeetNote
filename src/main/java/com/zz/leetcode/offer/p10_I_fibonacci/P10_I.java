package com.zz.leetcode.offer.p10_I_fibonacci;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pierce
 */
public class P10_I {
}

class Solution {
    private Map<Integer, Integer> m = new HashMap<>();
    public int fib(int n) {
        if (n < 2) return n;
        if (m.containsKey(n)) {
            return m.get(n);
        }
        int r = (fib(n - 1) + fib(n - 2)) % 1000000007;
        m.put(n, r);
        return r;
    }
}