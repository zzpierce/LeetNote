package com.zz.leetcode.p420_strong_password_checker;

import java.util.ArrayList;
import java.util.List;

/**
 * A password is considered strong if below conditions are all met:
 *
 * It has at least 6 characters and at most 20 characters.
 * It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
 * It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
 * Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password.
 * If s is already strong, return 0.
 *
 * Insertion, deletion or replace of any one character are all considered as one change.
 */
public class Problem420 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int d = solution.strongPasswordChecker("1010101010aaaB10101010");
        System.out.println(d);
    }

}

class Solution {

    public int strongPasswordChecker(String s) {

        int hasDigit = 0, hasUpper = 0, hasLower = 0;
        char[] ca = s.toCharArray();
        for (char c : ca) {
            if (isDigit(c)) {hasDigit = 1;}
            if (isUpper(c)) {hasUpper = 1;}
            if (isLower(c)) {hasLower = 1;}
        }

        int need = 3 - (hasDigit + hasUpper + hasLower);
        int modify = 0;

        if (s.length() < 6) {
            modify = 6 - s.length();
            //长度为5且所有字符一样时，需要两次改动
            if (s.length() == 5) {
                boolean same = true;
                for (int i = 0; i < s.length() - 1; i++) {
                    if (s.charAt(i) != s.charAt(i + 1)) {
                        same = false; break;
                    }
                }
                if (same) modify = 2;
            }
            return modify;
        }

        List<Seg> segList = toSegList(s);
        if (s.length() > 20) {
            //长度大于20时需要执行删除操作，删除到20个，再进行修改操作
            //3-5代价1 6-8代价2 9-11代价3
            //因此首先需要删除的是sameLength = 3/6/9/12，其次是sameLength=4/7/10/13，最后是5/8/11/14

            //step 1 将string分割为segmentList
            int len = s.length();
            while(len > 20) {
                boolean delete = false;
                for(Seg seg : segList) {
                    if (seg.size > 2 && seg.size % 3 == 0) {
                        seg.size --; len --; modify ++; delete = true; break;
                    }
                }
                if (!delete) {
                    for (Seg seg : segList) {
                        if (seg.size > 2 && seg.size % 3 == 1) {
                            seg.size --; len --; modify ++; delete = true; break;
                        }
                    }
                }
                if (!delete) {
                    for (Seg seg : segList) {
                        if (seg.size > 2 && seg.size % 3 == 2) {
                            seg.size --; len --; modify ++; delete = true; break;
                        }
                    }
                }
                if (!delete) {
                    for (Seg seg : segList) {
                        if (seg.size > 0) {
                            seg.size --; len --; modify ++; break;
                        }
                    }
                }
            }
        }
        int swi = 0;
        for (Seg seg : segList) {
            swi += seg.size / 3;
            modify += seg.size / 3;
        }

        if (need > swi) {
            modify += need - swi;
        }
        return modify;
    }

    private List<Seg> toSegList(String s) {
        List<Seg> segList = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < s.length(); i ++) {
            size ++;
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                segList.add(new Seg(s.charAt(i), size));
                size = 0;
            }
        }
        return segList;
    }

    private static class Seg {
        char c;
        int size;
        Seg(char c, int size) {
            this.c = c;
            this.size = size;
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isUpper(char c) {
        return c >='A' && c <= 'Z';
    }
}
