package com.zz.leetcode.offer.p31_push_and_pop;

import java.util.Stack;

/**
 * @author pierce
 */
public class P31 {
}

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();
        int x = 0;
        for (int i = 0; i < pushed.length; i ++) {
            int p = pushed[i];
            s.push(p);
            while (!s.empty() && s.peek() == popped[x]) {
                s.pop();
                x ++;
            }
        }
        return x == popped.length;
    }
}
