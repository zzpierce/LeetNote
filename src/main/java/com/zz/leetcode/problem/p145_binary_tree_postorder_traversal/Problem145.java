package com.zz.leetcode.problem.p145_binary_tree_postorder_traversal;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Problem145 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        root.right = n1;
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;

        List<Integer> list = new Solution().postorderTraversal(root);
        System.out.println(JSON.toJSONString(list));
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> integerList = new ArrayList<>();
        trav(integerList, root);
        return integerList;
    }

    private void trav(List<Integer> list, TreeNode node) {
        if (node != null) {
            trav(list, node.left);
            trav(list, node.right);
            list.add(node.val);
        }
    }

}