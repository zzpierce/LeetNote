package com.zz.leetcode.offer.p26_is_sub_tree;

public class P26 {
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
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return cpr(A, B);
    }

    public boolean cpr(TreeNode a, TreeNode b) {
        if (a == null || b == null) return false;
        if (cp(a, b)) return true;
        if (cpr(a.left, b)) return true;
        if (cpr(a.right, b)) return true;
        return false;
    }

    public boolean cp(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null) return false;
        if (b == null) return true;
        if (a.val != b.val) return false;
        if (cp(a.left, b.left) && cp(a.right, b.right)) {
            return true;
        }
        return false;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}