package com.zz.leetcode.p488_zuma_game;

import java.util.HashMap;
import java.util.Map;

public class Problem488 {


    public static void main(String[] args) {
        Solution s = new Solution();
        int d = s.findMinStep("R", "RR");

        System.out.println(d);
    }

}

class Solution {

    private int M = Integer.MAX_VALUE - 1000;

    public int findMinStep(String board, String hand) {
        Map<Character, Integer> handMap = new HashMap<>();
        for (int i = 0; i < hand.length(); i ++) {
            Character c = hand.charAt(i);
            if (handMap.containsKey(c)) {
                handMap.put(c, handMap.get(c) + 1);
            } else {
                handMap.put(c, 1);
            }
        }
        int r = zuma(board, handMap);
        return r == M ? -1 : r;
    }

    private int zuma(String board, Map<Character, Integer> handMap) {
        if (board.length() == 0) return 0;
        int count = 0;
        int res = M;
        for (int i = 0; i < board.length(); i ++) {
            count ++;
            if (i == board.length() - 1 || board.charAt(i) != board.charAt(i + 1)) {
                Character c = board.charAt(i);
                if (handMap.containsKey(c) && handMap.get(c) + count >= 3) {
                    int hc = handMap.get(c);
                    String sub = board.substring(0, i - count + 1) + board.substring(i + 1);
                    String ectSub = ect(sub);
                    handMap.put(c, hc - (3 - count));
                    res = Math.min(res, zuma(ectSub, handMap) + 3 - count);
                    handMap.put(c, hc);
                    count = 0;
                } else {
                    count = 0;
                }
            }
        }
        return res;
    }

    private String ect(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i ++) {
            count ++;
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (count >= 3) {
                    String sub = s.substring(0, i - count + 1) + s.substring(i + 1);
                    //System.out.println(sub);
                    return ect(sub);
                } else {
                    count = 0;
                }
            }
        }
        return s;
    }
}