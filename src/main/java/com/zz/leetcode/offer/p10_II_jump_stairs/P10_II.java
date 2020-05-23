package com.zz.leetcode.offer.p10_II_jump_stairs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pierce
 */
public class P10_II {
}

class Solution {
    private Map<Integer, Integer> m = new HashMap<>();
    public int numWays(int n) {
        if (n < 2) return 1;
        if (m.containsKey(n)) {
            return m.get(n);
        }
        int r = (numWays(n - 1) + numWays(n - 2)) % 1000000007;
        m.put(n, r);
        return r;
    }
}
