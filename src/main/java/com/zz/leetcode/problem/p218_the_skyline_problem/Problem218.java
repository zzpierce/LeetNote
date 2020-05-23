package com.zz.leetcode.problem.p218_the_skyline_problem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author pierce
 */
public class Problem218 {
    public static void main(String[] args) {
        int[][] bs = new int[5][];
        bs[0] = new int[] {2,9,10};
        bs[1] = new int[] {3,7,15};
        bs[2] = new int[] {5,12,12};
        bs[3] = new int[] {15,20,10};
        bs[4] = new int[] {19,24,8};

        Solution s = new Solution();
        List<int[]> ans = s.getSkyline(bs);

        for (int[] a : ans) {
            System.out.println(a[0] + "--" + a[1]);
        }

    }
}

class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<B> bs = new ArrayList<>();
        for (int[] b : buildings) {
            bs.add(new B(b[0], b[1], b[2]));
        }

        bs.sort(Comparator.comparingInt(v -> v.l));
        int len = bs.size();

        List<int[]> ans = new ArrayList<>();
        int cx = -1, cy = 0, cr = Integer.MAX_VALUE;
        int p = 0;

        while (p < len) {
            int maxH = 0, tempR = Integer.MAX_VALUE, tempIndex = p + 1;
            for (int i = p; i < len; i ++) {
                B b = bs.get(i);

                if (b.l > cr) {
                    ans.add(new int[] {cr, maxH});
                    cx = cr;
                    cy = maxH;
                    cr = tempR;
                    p = tempIndex;
                    break;
                }

                if (b.h > cy) {
                    ans.add(new int[] {b.l, b.h});
                    cx = b.l;
                    cy = b.h;
                    cr = b.r;
                    p = i + 1;
                    break;
                }

                if (b.r > cr && b.h > maxH) {
                    maxH = b.h;
                    tempR = b.r;
                    tempIndex = i;
                }

            }
        }

        //add last one
        ans.add(new int[] {cr, 0});

        return ans;
    }


    class B {
        int l;
        int r;
        int h;

        B(int a, int b, int c) {
            l = a;
            r = b;
            h = c;
        }
    }
}
