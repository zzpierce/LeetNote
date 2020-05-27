package com.zz.leetcode.offer.p33_after_order_tree;

public class P33 {
}

class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) return true;
        return check(0, postorder.length - 1, postorder);
    }

    private boolean check(int s, int e, int[] r) {
        if (s == e) return true;
        int root = r[e];
        int dis = -1;
        for (int i = s; i < e; i ++) {
            if (r[i] > root) {
                dis = i; break;
            }
        }
        if (dis != -1) {
            for (int i = dis; i < e; i++) {
                if (r[i] < root) return false;
            }
        }
        if (dis == -1 || dis == s) {
            return check(s, e - 1, r);
        }
        return check(s, dis - 1, r) && check(dis, e - 1, r);
    }
}
