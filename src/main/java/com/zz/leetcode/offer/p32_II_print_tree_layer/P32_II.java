package com.zz.leetcode.offer.p32_II_print_tree_layer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class P32_II {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        Solution s = new Solution();
        s.levelOrder(root);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<T> q = new ArrayDeque<>();
        T rt = new T(root, 0);
        q.add(rt);

        List<Integer> cl = new ArrayList<>();
        int p = 0;
        while (!q.isEmpty()) {
            T t = q.poll();
            if (t.layer != p) {
                ans.add(cl);
                cl = new ArrayList<>();
                p ++;
            }
            cl.add(t.n.val);
            if (t.n.left != null) {
                q.add(new T(t.n.left, t.layer + 1));
            }
            if (t.n.right != null) {
                q.add(new T(t.n.right, t.layer + 1));
            }
        }
        if (!cl.isEmpty()) ans.add(cl);
        return ans;
    }

    class T {
        TreeNode n;
        int layer;
        T(TreeNode node, int l) {
            n = node; layer = l;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}