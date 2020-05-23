package com.zz.leetcode.offer.p07_rebuild_binary_tree;

/**
 * @author pierce
 */
public class P07 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] p = new int[]{1,2};
        int[] i = new int[]{2,1};
        TreeNode t = s.buildTree(p, i);
        System.out.println(t);
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        TreeNode r = buildNode(0, preorder.length - 1, preorder, 0, inorder.length - 1, inorder);
        return r;
    }

    private TreeNode buildNode(int s1, int e1, int[] pre, int s2, int e2, int[] inp) {
        TreeNode root = new TreeNode(pre[s1]);
        if (s1 == e1) return root;
        int rootp = find(pre[s1], s2, e2, inp);
        int leftp = rootp - s2;
        int rightp = e2 - rootp;
        if (leftp > 0) {
            root.left = buildNode(s1 + 1, s1 + leftp, pre, s2, rootp - 1, inp);
        }
        if (rightp > 0) {
            root.right = buildNode(s1 + leftp + 1, e1, pre, rootp + 1, e2, inp);
        }
        return root;
    }

    private int find(int x, int s, int e, int[] ar) {
        for (int i = s; i <= e; i ++) {
            if (ar[i] == x) return i;
        }
        return -1;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}