package com.zz.leetcode.offer.p34_tree_path_sum;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P34 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(8);
        root.left = left; root.right = right;
        left.left = new TreeNode(11);
        left.left.left = new TreeNode(7);
        left.left.right = new TreeNode(2);
        right.left = new TreeNode(13);
        right.right = new TreeNode(4);
        right.right.left = new TreeNode(5);
        right.right.right = new TreeNode(1);

        Solution s=  new Solution();
        s.pathSum(root, 22);
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

    List<Integer> s = new ArrayList<>();
    List<List<Integer>> ss;
    int p = 0;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ss = new ArrayList<>();
        if (root != null) {
            t(root, 0, sum);
        }
        return ss;
    }

    public void t(TreeNode i, int v, int sum) {
        s.add(i.val);
        if (i.left == null && i.right == null && v + i.val == sum) {
            ss.add(new ArrayList<>(s));
        }
        if (i.left != null) {
            t(i.left, v + i.val, sum);
        }
        if (i.right != null) {
            t(i.right, v + i.val, sum);
        }
        s.remove(s.size() - 1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}