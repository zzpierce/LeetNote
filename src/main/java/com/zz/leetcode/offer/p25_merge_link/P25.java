package com.zz.leetcode.offer.p25_merge_link;

public class P25 {
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        ListNode head = new ListNode(0);
        ListNode h = head;
        while (p != null || q != null) {
            if (p == null) {
                h.next = new ListNode(q.val); q = q.next;
            } else if (q == null) {
                h.next = new ListNode(p.val); p = p.next;
            } else {
                if (p.val > q.val) {
                    h.next = new ListNode(q.val); q = q.next;
                } else {
                    h.next = new ListNode(p.val); p = p.next;
                }
            }
            h = h.next;
        }
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}