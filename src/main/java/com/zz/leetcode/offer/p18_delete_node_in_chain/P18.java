package com.zz.leetcode.offer.p18_delete_node_in_chain;

/**
 * @author pierce
 */
public class P18 {
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
    public ListNode deleteNode(ListNode head, int val) {
        if (head != null) {
            if (head.val == val) return head.next;
            ListNode i = head, pre = head;
            while (i != null && i.val != val) {
                pre = i;
                i = i.next;
            }
            if (i != null) {
                pre.next = i.next;
            }
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}