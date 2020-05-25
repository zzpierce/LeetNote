package com.zz.leetcode.offer.p22_link_reverse_count;

public class P22 {
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
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p = head, q = head;
        for (int i = 0; i < k; i ++) {
            if (p != null) p = p.next;
        }
        while (p != null) {
            p = p.next; q = q.next;
        }
        return q;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}