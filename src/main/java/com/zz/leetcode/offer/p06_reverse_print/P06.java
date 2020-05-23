package com.zz.leetcode.offer.p06_reverse_print;

import java.util.Stack;

/**
 * @author pierce
 */
public class P06 {
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> s = new Stack<>();
        while (head != null) {
            s.push(head.val);
            head = head.next;
        }
        int[] r = new int[s.size()];
        int i = 0;
        while (!s.empty()) {
            r[i ++] = s.pop();
        }
        return r;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}