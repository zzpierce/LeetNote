package com.zz.leetcode.contest.s193.pd_kth_ancestor_of_a_tree_node;

import java.util.*;

/**
 * @author pierce
 */
public class P4 {
    public static void main(String[] args) {
        TreeAncestor t = new TreeAncestor(7, new int[]{-1,0,0,1,1,2,2});
        System.out.println(t.getKthAncestor(3,1));
        System.out.println(t.getKthAncestor(5,2));
        System.out.println(t.getKthAncestor(6,3));
    }
}

/**
 * [9.0]
 * Binary Lifting算法
 * dp思想
 * n的第2^j个祖先为
 * f(n, j) = f(f(n, j-1), j-1)
 */
class TreeAncestor {

    int[][] pr;

    public TreeAncestor(int n, int[] parent) {
        pr = new int[n][17];
        for (int i = 0; i < n; i ++) {
            Arrays.fill(pr[i], -1);
        }
        for (int i = 0; i < n; i ++) {
            pr[i][0] = parent[i];
        }

        for (int j = 1; ; j++) {
            boolean br = true;
            for (int i = 0; i < n; i ++) {
                int half = pr[i][j-1];
                if (half != -1) {
                    int k = pr[pr[i][j - 1]][j - 1];
                    pr[i][j] = k;
                    if (k != -1) br = false;
                }
            }
            if (br) break;
        }
    }


    public int getKthAncestor(int node, int k) {
        int r = 1 << 18; int t = 18;
        while (k > 0) {
            while (r > k) {
                r >>= 1; t --;
            }
            k -= r;
            node = pr[node][t];
            if (node == -1) return -1;
        }
        return node;
    }

}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */