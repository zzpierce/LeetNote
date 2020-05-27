package com.zz.leetcode.offer.p32_III_print_tree_layer;

import java.util.*;

public class P32_III {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

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
        q.add(new T(root, 0, false));
        int p = 0;
        List<Integer> cl = new ArrayList<>();

        while (!q.isEmpty()) {
            Stack<T> s = new Stack<>();
            while (!q.isEmpty() && q.peek().layer == p) {
                T t = q.poll();
                cl.add(t.n.val);
                s.push(t);
            }
            ans.add(cl);
            cl = new ArrayList<>();
            p ++;
            while (!s.empty()) {
                T t = s.pop();
                if (t.left) {
                    if (t.n.left != null) q.add(new T(t.n.left, t.layer + 1, !t.left));
                    if (t.n.right != null) q.add(new T(t.n.right, t.layer + 1, !t.left));
                } else {
                    if (t.n.right != null) q.add(new T(t.n.right, t.layer + 1, !t.left));
                    if (t.n.left != null) q.add(new T(t.n.left, t.layer + 1, !t.left));
                }
            }
        }
        return ans;
    }

    class T {
        TreeNode n;
        int layer;
        boolean left;
        T(TreeNode node, int l, boolean le) {
            n = node; layer = l; left = le;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}