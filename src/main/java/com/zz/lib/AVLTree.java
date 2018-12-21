package com.zz.lib;

import java.util.HashMap;

public class AVLTree<E> {

    private AVLNode<E> root;

    public void insert(E e) {
        if (root == null) root = new AVLNode<>(e, 0);
        else {
            doInsert(root, e);
        }
    }

    private void doInsert(AVLNode<E> node, E e) {
        if (node == null) node = new AVLNode<>(e);
        if (compare(e, node.data) < 0) {
            doInsert(node.left, e);
            if (height(node.left) - height(node.right) == 2) {
                if (compare(e, node.left.data) < 0) {
                    //LL
                    node = rotateLL(node);
                } else if (compare(e, node.left.data) > 0) {
                    node = rotateLR(node);
                }
            }
            if (node.left == null) {
                node.left = new AVLNode<>(e, node.height + 1);
            } else {
                doInsert(node.left, e);
            }
        } else if (compare(e, node.data) > 0) {
            if (node.right == null) {
                node.right = new AVLNode<>(e, node.height + 1);
            } else {
                doInsert(node.right, e);
            }
        }
        node.height = Math.max(height(node.left), height(node.right));
    }

    private AVLNode<E> rotateLL(AVLNode<E> node) {
        AVLNode<E> p = node.left;
        node.left = p.right;
        p.right = node;
        //recalculate height
        node.height = Math.max(height(node.left), height(node.right));
        p.height = Math.max(height(p.left), height(node));
        return p;
    }

    private AVLNode<E> rotateRR(AVLNode<E> node) {
        AVLNode<E> p = node.right;
        node.right = p.left;
        p.left = node;
        node.height = Math.max(height(node.left), height(node.right));
        p.height = Math.max(height(node), height(p.right));
        return p;
    }

    private AVLNode<E> rotateLR(AVLNode<E> node) {
        node.left = rotateRR(node.left);
        return rotateLL(node);
    }

    private AVLNode<E> rotateRL(AVLNode<E> node) {
        node.right = rotateLL(node.right);
        return rotateRR(node);
    }

    private int compare(E e1, E e2) {
        return (Integer)e1 - (Integer)e2;
    }

    private int height(AVLNode<E> p) {
        return p == null ? -1 : p.height;
    }

    private class AVLNode<T> {

        private T data;

        private AVLNode<T> left;

        private AVLNode<T> right;

        private int height;

        private AVLNode(T data, int height) {
            this.data = data;
            this.height = height;
        }

        private AVLNode(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 9; i > 0; i --) {
            tree.insert(i);
        }
    }
}


