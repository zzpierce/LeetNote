package com.zz.leetcode.offer.p24_reverse_link;

public class P24 {
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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = head.next.next, q = head.next, r = head;
        r.next = null;
        while (p != null) {
            q.next = r;
            r = q;
            q = p;
            p = p.next;
        }
        q.next = r;
        return q;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}