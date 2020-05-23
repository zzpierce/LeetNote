package com.zz.leetcode.problem.p140_word_break_II;

import java.util.*;

/**
 * @author pierce
 */
public class Problem140 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("aa");
        list.add("aaa");

        Solution s = new Solution();
        List<String> r = s.wordBreak("aaaaaaaaa", list);

        for (String str :r ) {
            System.out.println(str);
        }
    }
}

class Solution {

    private List<String>[] jump;
    private List<String> EMPTY = new ArrayList<>();
    private Stack<String> printStack;
    private List<String> result;

    public List<String> wordBreak(String s, List<String> wordDict) {
        //init jump array
        int len = s.length();
        jump = new ArrayList[len];
        jump[0] = new ArrayList<>();
        boolean valid = false;
        for (int i = 0; i < len; i ++) {
            if (jump[i] != null) {
                for (String word : wordDict) {
                    int wlen = word.length();
                    if (i + wlen <= len) {
                        if (s.substring(i, i + wlen).equals(word)) {
                            jump[i].add(word);
                            if (i + wlen == len) {
                                valid = true;
                            } else {
                                if (jump[i + wlen] == null) {
                                    jump[i + wlen] = new ArrayList<>();
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!valid) {
            return EMPTY;
        }
        printStack = new Stack<>();
        result = new ArrayList<>();

        //print
        dfs(0, s);

        return result;
    }

    private void dfs(int index, String s) {
        if (jump[index] != null) {
            for (String str : jump[index]) {
                if (index + str.length() == s.length()) {
                    addToList(str);
                } else {
                    printStack.push(str);
                    dfs(index + str.length(), s);
                    printStack.pop();
                }
            }
        }
    }

    private void addToList(String s) {
        StringBuilder b = new StringBuilder();
        for (String str : printStack) {
            b.append(str).append(" ");
        }
        b.append(s);
        result.add(b.toString());
    }

}

