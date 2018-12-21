package com.zz.lib;

import com.sun.javafx.image.IntPixelGetter;

public class SearchBinaryTree<E> {

    TreeNode<E> root;

    public SearchBinaryTree() {
    }

    void insert(E e) {
        if (root == null) {
            root = new TreeNode<>(e);
        } else {
            TreeNode<E> p = root;
            while (true) {
                if (compare(e, p.value) == 1) {
                    if (p.right != null) p = p.right;
                    else {
                        p.right = new TreeNode<>(e);
                        break;
                    }
                } else if (compare(e, p.value) == 0) {
                    if (p.left != null) p = p.left;
                    else {
                        p.left = new TreeNode<>(e);
                        break;
                    }
                }
            }
        }
    }

    void delete(E e) {
        //delete the first element which equals to e
        if (root == null) return;
        TreeNode<E> p = root;
    }

    void preOrderTraversal() {
        travel(root);
    }

    private void travel(TreeNode<E> p) {
        if (p == null) return;
        travel(p.left);
        System.out.println(p.value);
        travel(p.right);
    }

    private int compare(E e1, E e2) {
        return (Integer)e1 > (Integer)e2 ? 1 : 0;
    }

    public static void main(String[] args) {
        SearchBinaryTree<Integer> tree = new SearchBinaryTree<>();
        for (int i = 0; i < 100; i ++) {
            tree.insert((int)(Math.random() * 1000));
        }
        tree.preOrderTraversal();
    }

}

class TreeNode<E> {

    TreeNode(E e) {
        value = e;
    }

    TreeNode<E> left;
    TreeNode<E> right;

    E value;
}