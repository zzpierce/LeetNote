package com.zz.leetcode.contest.s191.p41;

/**
 * @author pierce
 */
public class P41 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] b = new int[]{2,1,1};
        double r=  s.getProbability(b);
        System.out.println(r);
    }
}

class Solution {

    int[] balls;
    int[] b;
    int r1 = 0, r2 = 0;

    public double getProbability(int[] balls) {
        this.balls = balls;
        b = new int[balls.length];
        int sum = 0;
        for (int i = 0; i < balls.length; i ++) {
            sum += balls[i]; b[i] = balls[i];
        }
        int s = sum / 2;
        t(s);
        return r1 / (double)r2;
    }

    public void t(int s) {
        if (s == 0) {
            r1 += c(); r2 ++;
        } else {
            for (int i = 0; i < b.length; i ++) {
                if (b[i] > 0) {
                    b[i] --;
                    t(s - 1);
                    b[i] ++;
                }
            }
        }
    }

    public int c() {

        int r1 = 0, r2 = 0;
        for (int i = 0; i < b.length; i ++) {
            if (b[i] == 0) r2 ++;
            else if (b[i] == balls[i]) r1 ++;
            else {
                r1 ++;
                r2 ++;
            }
        }
        for (int i = 0; i < b.length; i ++) {
            System.out.print(b[i] + " - ");
        }
        System.out.print("[" + (r1 == r2 ? 1 : 0) + "]");
        System.out.println("");
        return r1 == r2 ? 1 : 0;
    }
}