package com.zz.leetcode.problem.p126_word_ladder_II;

import java.util.*;

public class Problem126 {

    public static void main(String[] args) {

        Solution s = new Solution();
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        List<List<String>> ans = s.findLadders("hit", "cog", wordList);
        System.out.println(ans);

    }

}


class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(beginWord)) {
            wordList.add(0, beginWord);
        }
        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }
        int size = wordList.size();
        List<Word> ws = new ArrayList<>(size);
        Map<String, Word> wmap = new HashMap<>();
        Word beginNode = new Word(beginWord, 0);
        ws.add(beginNode);
        wmap.put(beginWord, beginNode);

        boolean stop = false;
        for (int i = 0; i < ws.size(); i ++) {
            Word w = ws.get(i);
            for (int j = 0; j < size; j ++) {
                String sj = wordList.get(j);
                if (diff(w.s, sj) == 1) {
                    if (!wmap.containsKey(sj)) {
                        Word nw = new Word(sj, w.level + 1);
                        wmap.put(sj, nw);
                        if (!stop) {
                            ws.add(nw);
                            w.next.add(nw);
                        }
                        if (sj.equals(endWord)) {
                            stop = true;
                        }
                    } else if (wmap.get(sj).level > w.level) {
                        Word nw = wmap.get(sj);
                        w.next.add(nw);
                    }
                }
            }
        }

        Word end = ws.get(ws.size() - 1);
        if (!end.s.equals(endWord)) {
            return new ArrayList<>();
        }

        int v = end.level;
        Stack<String> stack = new Stack<>();
        List<List<String>> wordAnswer = new ArrayList<>();
        dfs(ws.get(0), endWord, stack, wordAnswer);

        return wordAnswer;
    }

    void dfs(Word w, String end, Stack<String> current, List<List<String>> ans) {
        current.push(w.s);
        if (w.s.equals(end)) {
            ans.add(new ArrayList<>(current));
        } else {
            List<Word> ws = w.next;
            if (ws != null && ws.size() > 0) {
                for (Word d : ws) {
                    dfs(d, end, current, ans);
                }
            }
        }
        current.pop();
    }

    int diff(String s1, String s2) {
        int d = 0;
        for (int i = 0; i < s1.length(); i ++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                d ++;
            }
        }
        return d;
    }

    class Word {
        String s;
        int level;
        List<Word> next;

        public Word(String s, int level) {
            this.s = s;
            this.next = new ArrayList<>();
            this.level = level;
        }
    }
}