package com.zz.leetcode.offer.p27_tree_mirror;

public class P27 {
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
    public TreeNode mirrorTree(TreeNode root) {
        r(root);
        return root;
    }

    private void r(TreeNode a) {
        if (a == null) return;
        r(a.left);
        r(a.right);
        if (a.left == null) {
            a.left = a.right; a.right = null;
        }
        else if (a.right == null) {
            a.right = a.left; a.left = null;
        }
        else {
            TreeNode n = a.left;
            a.left = a.right;
            a.right = n;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}