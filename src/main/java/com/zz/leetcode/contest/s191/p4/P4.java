package com.zz.leetcode.contest.s191.p4;

/**
 * @author pierce
 */
public class P4 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] b = new int[]{2,1,1};
        double r=  s.getProbability(b);
        System.out.println(r);
    }
}

class Solution {

    int[] p;
    int[] balls;
    int r1 = 0, r2 = 0;

    public double getProbability(int[] balls) {
        this.balls = balls;
        p = new int[balls.length];
        int sum = 0;
        for (int i = 0; i < balls.length; i ++) sum += balls[i];
        int s = sum / 2;
        t(0, s);
        return r1 / (double)r2;
    }

    public void t(int x, int s) {
        if (s == 0) {
            r1 += c(); r2 ++;
        } else if (x < p.length) {
            int i = 0;
            while (p[x] <= balls[x] && s - i >= 0) {
                t(x + 1, s - i);
                p[x] ++;
                i ++;
            }
            p[x] -= i;
        }
    }

    public int c() {
        for (int i = 0; i < p.length; i ++) {
            System.out.print(p[i] + " - ");
        }
        System.out.println("");
        int r1 = 0, r2 = 0;
        for (int i = 0; i < p.length; i ++) {
            if (p[i] == 0) r2 ++;
            else if (p[i] == balls[i]) r1 ++;
            else {
                r1 ++;
                r2 ++;
            }
        }
        return r1 == r2 ? 1 : 0;
    }
}