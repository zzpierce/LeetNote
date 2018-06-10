package com.zz.leetcode.p25_reverse_nodes_in_k_groups;

import java.util.Stack;

/**
 * @author pierce
 */

public class Problem25 {
    public static void main(String[] args) {
    }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<Integer> s = new Stack<>();
        ListNode p = head, p1 = head;
        int kp = k;
        while (p != null) {
            s.push(p.val);
            p = p.next;
            kp --;
            if (kp == 0) {
                while (p1 != p) {
                    p1.val = s.pop();
                    p1 = p1.next;
                }
                kp = k;
            }
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}