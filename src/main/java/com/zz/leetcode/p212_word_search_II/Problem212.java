package com.zz.leetcode.p212_word_search_II;

import java.util.*;

/**
 * @author pierce
 */
public class Problem212 {
    public static void main(String[] args) {
        char[][] board = new char[4][];
        board[0] = new char[] {'o', 'a', 'a', 'n'};
        board[1] = new char[] {'e', 't', 'a', 'e'};
        board[2] = new char[] {'i', 'h', 'k', 'r'};
        board[3] = new char[] {'i', 'f', 'l', 'v'};

        String[] w = new String[] {"oath", "pea", "eat", "rain"};

//        char[][] board = new char[1][];
//        board[0] = new char[] {'a'};
//        String[] w = new String[] {"a"};

        List<String> ans = new Solution().findWords(board, w);

        for (String s : ans) {
            System.out.println(s);
        }
    }
}

class Solution {

    private Set<String> set;
    private int m, n;

    public List<String> findWords(char[][] board, String[] words) {
        set = new HashSet<>();
        if (board.length == 0 || board[0].length == 0) {
            return new ArrayList<>();
        }

        m = board.length;
        n = board[0].length;

        Node root = buildTree(words);
        for (int i = 0; i < board.length; i ++) {
            char[] line = board[i];
            for (int j = 0; j < line.length; j ++) {
                dfs(i, j, board, root);
            }
        }

        List<String> result = new ArrayList<>();
        result.addAll(set);

        return result;
    }

    private void dfs(int i, int j, char[][] board, Node node) {
        if (board[i][j] != '#' && node.map.containsKey(board[i][j])) {
            node = node.map.get(board[i][j]);
            if (node.end) {
                set.add(node.str);
            }
            board[i][j] = '#';
            if (i > 0) {
                dfs(i - 1, j, board, node);
            }
            if (i < m - 1) {
                dfs(i + 1, j, board, node);
            }
            if (j > 0) {
                dfs(i, j - 1, board, node);
            }
            if (j < n - 1) {
                dfs(i, j + 1, board, node);
            }
            board[i][j] = node.v;
        }
    }

    private Node buildTree(String[] words) {
        Node root = new Node('r');
        Node p;
        for (String s : words) {
            p = root;
            char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; i ++) {
                char c  = charArray[i];
                if (p.map.containsKey(c)) {
                    p = p.map.get(c);
                } else {
                    p.map.put(c, new Node(c));
                    p = p.map.get(c);
                }
                if (i == charArray.length - 1) {
                    p.end = true;
                    p.str = s;
                }
            }
        }

        return root;
    }

    class Node {
        char v;
        boolean end;
        String str;
        HashMap<Character, Node> map;

        Node(char v) {
            end = false;
            this.v = v;
            this.map = new HashMap<>();
        }
    }

}

