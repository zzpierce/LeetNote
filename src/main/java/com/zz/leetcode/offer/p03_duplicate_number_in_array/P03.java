package com.zz.leetcode.offer.p03_duplicate_number_in_array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pierce
 */
public class P03 {
}

class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> s = new HashSet<>(nums.length);
        for (int i : nums) {
            if (s.contains(i)) {
                return i;
            } else {
                s.add(i);
            }
        }
        return 0;
    }
}