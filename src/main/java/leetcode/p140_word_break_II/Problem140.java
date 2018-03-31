package leetcode.p140_word_break_II;

import java.util.*;

/**
 * @author pierce
 */
public class Problem140 {
    public static void main(String[] args) {
        List<String> src = new ArrayList<>();
        src.add("a");
        src.add("aa");
        src.add("aaa");
        src.add("aaaa");
        src.add("aaaaa");

        Solution s = new Solution();
        List<String> ans = s.wordBreak("aaaaaa", src);
        for (String str : ans) {
            System.out.println(str);
        }
    }
}

class Solution {

    private List<String> EMPTY = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {

        CTree cTree = new CTree();
        for (String word : wordDict) {
            cTree.addWord(word);
        }

        for (char c : s.toCharArray()) {
            if (!cTree.cs.contains(c)) {
                return EMPTY;
            }
        }

        List<String> ans = getStr(s, 0, cTree);

        return ans;

    }

    private List<String> getStr(String s, int p, CTree tree) {

        if (p >= s.length()) return EMPTY;

        Node np = tree.root;
        List<String> list = new ArrayList<>();

        int p2 = p;
        while (p2 < s.length()) {
            char c = s.charAt(p2);

            if (np.next == null || !np.next.containsKey(c)) {
                return list;
            }

            np = np.next.get(c);
            if (np.end) {
                String prefix = s.substring(p, p2 + 1);

                if (p2 == s.length() - 1) {
                    list.add(prefix);
                    break;
                }

                List<String> tmp = getStr(s, p2 + 1, tree);
                for (String ts : tmp) {
                    list.add(prefix + " " + ts);
                }
            }

            p2 ++;
        }

        return list;
    }


}

class CTree {

    Set<Character> cs;
    Node root;

    public CTree() {
        root = new Node('R', false);
        cs = new HashSet<>();
    }

    public void addWord(String word) {

        int l = word.length();
        Node np = root;
        boolean end = false;

        for (int i = 0; i < l; i ++) {
            if (i == l - 1) end = true;

            char c = word.charAt(i);
            if (!cs.contains(c)) {
                cs.add(c);
            }
            if (np.next == null || !np.next.containsKey(c)) {
                np.addNext(c, end);
            } else if (end) {
                np.addEnd(c);
            }
            np = np.next.get(c);
        }
    }

}

class Node {

    char c;
    boolean end;
    Map<Character, Node> next;

    public Node(char c, boolean end) {
        this.c = c;
        this.end = end;
    }


    public void addNext(char c, boolean end) {
        if (next == null) next = new HashMap<>();

        Node newNode = new Node(c, end);
        next.put(c, newNode);
    }

    public void addEnd(char c) {
        Node n = next.get(c);
        n.end = true;
    }

}
