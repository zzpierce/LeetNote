package com.zz.lib;

/**
 * @author pierce
 */
public class BRTree<K, V> {

    TreeNode root;

    BRTree() {
        root = null;
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new TreeNode(key, value, false);
        }
    }


    class TreeNode {

        TreeNode(K key, V value, boolean red) {
            this.key = key;
            this.value = value;
            this.red = red;
        }

        K key;
        V value;
        boolean red;

        TreeNode left;
        TreeNode right;
    }

}
