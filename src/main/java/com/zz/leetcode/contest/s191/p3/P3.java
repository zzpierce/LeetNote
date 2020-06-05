package com.zz.leetcode.contest.s191.p3;

import com.zz.util.ReadUtil;

import java.util.*;

/**
 * @author pierce
 */
public class P3 {
    public static void main(String[] args) {
        int[][] c = ReadUtil.read("[[0,1],[1,3],[2,3],[4,0],[4,5]]");
        Solution s= new Solution();
        int r = s.minReorder(6, c);
        System.out.println(r);
    }
}

class Solution {

    Map<Integer, Set<P>> m = new HashMap<>();

    public int minReorder(int n, int[][] connections) {
        for (int[] c : connections) {
            if (!m.containsKey(c[0])) m.put(c[0], new HashSet<>());
            if (!m.containsKey(c[1])) m.put(c[1], new HashSet<>());

            m.get(c[0]).add(new P(c[1], 1));
            m.get(c[1]).add(new P(c[0], 0));
        }

        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> path = new HashSet<>();
        q.add(0);
        int res = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            path.add(v);
            Set<P> ps = m.get(v);
            for (P p : ps) {
                if (!path.contains(p.to)) {
                    if (p.y == 1) res ++;
                    q.add(p.to);
                    path.add(p.to);
                }
            }
        }
        return res;
    }

    class P {
        int to;
        int y;
        P(int t, int y) {
            this.to = t;
            this.y = y;
        }
    }
}