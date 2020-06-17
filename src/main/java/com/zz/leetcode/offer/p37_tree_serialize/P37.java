package com.zz.leetcode.offer.p37_tree_serialize;

import java.util.*;

public class P37 {
    public static void main(String[] args) {
        Codec c = new Codec();
        TreeNode root = new TreeNode(3);
        TreeNode left = null;
        TreeNode right = new TreeNode(4);
        root.left = left;
        root.right = right;
        right.right = new TreeNode(1);
        System.out.println(c.serialize(root));

        String r = c.serialize(root);
        TreeNode r1 = c.deserialize(r);
        System.out.println(r1.val);
    }
}


class Codec {

    private int NL = -3241;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        List<Integer> ar = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        ar.add(root.val);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            ar.add(n.left == null ? NL : n.left.val);
            if (n.left != null) q.add(n.left);
            ar.add(n.right == null ? NL : n.right.val);
            if (n.right != null) q.add(n.right);
        }
        return join(ar);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("")) return null;
        List<Integer> l = parse(data);
        Queue<TreeNode> q = new ArrayDeque<>();
        int headV = l.get(0);
        TreeNode root = new TreeNode(headV);
        q.add(root);
        int i = 0;
        while(!q.isEmpty()) {
            TreeNode t = q.poll();
            int leftV = l.get(++i);
            int rightV = l.get(++i);
            if (leftV != NL) {
                TreeNode leftNode = new TreeNode(leftV);
                t.left = leftNode;
                q.add(leftNode);
            }
            if (rightV != NL) {
                TreeNode rightNode = new TreeNode(rightV);
                t.right =rightNode;
                q.add(rightNode);
            }
        }
        return root;
    }

    private String join(List<Integer> ar) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < ar.size(); i ++) {
            b.append(ar.get(i));
            if (i != ar.size() - 1) {
                b.append(",");
            }
        }
        return b.toString();
    }

    private List<Integer> parse(String s) {
        String[] ss = s.split(",");
        List<Integer> l = new ArrayList<>(ss.length);
        for (String t : ss) {
            l.add(Integer.parseInt(t));
        }
        return l;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root)
//
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

