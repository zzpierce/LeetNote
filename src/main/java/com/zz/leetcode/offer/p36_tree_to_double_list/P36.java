package com.zz.leetcode.offer.p36_tree_to_double_list;

public class P36 {
    public static void main(String[] args) {
        Node root = new Node(4);
        Node left = new Node(2);
        left.left = new Node(1);
        left.right = new Node(3);
        root.left = left;
        root.right = new Node(5);

        Solution s = new Solution();
        s.treeToDoublyList(root);
    }
}


class Solution {

    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        Node p = first(root); Node l = last(root);
        r(root);
        p.left = l;
        l.right = p;
//        Node head = new Node(0);
//        head.right = p;
        return p;
    }

    public void r(Node n) {
        Node l = last(n.left);
        if (l != null) {
            r(n.left);
            n.left = l;
            l.right = n;
        }
        Node r = first(n.right);
        if (r != null) {
            r(n.right);
            n.right = r;
            r.left = n;
        }
    }

    public Node first(Node n) {
        if (n == null) return null;
        if (n.left != null) {
            return first(n.left);
        }
        return n;
    }

    public Node last(Node n) {
        if (n == null) return null;
        if (n.right != null) {
            return last(n.right);
        }
        return n;
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

