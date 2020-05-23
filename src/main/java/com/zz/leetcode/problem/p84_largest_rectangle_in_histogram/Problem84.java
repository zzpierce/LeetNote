package com.zz.leetcode.problem.p84_largest_rectangle_in_histogram;

import java.util.Stack;

/**
 * @author pierce
 */
public class Problem84 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Node> s = new Stack<>();
        int len = heights.length;
        int[] ha = new int[len + 2];
        for (int i = 0; i < len; i ++) {
            ha[i + 1] = heights[i];
        }
        ha[0] = 0;
        ha[len + 1] = 0;
        int ans = 0;
        Node lastPop = new Node(0, 0);
        for (int i = 0; i <= len + 1; i ++) {
            int h = ha[i];
            if (i == 0 || h > ha[i - 1]) {
                s.push(new Node(i, ha[i]));
            }
            else if (h < ha[i - 1]) {
                while(!s.empty()) {
                    Node topNode = s.peek();
                    if (topNode.h > h) {
                        int val = (i - topNode.index) * topNode.h;
                        if (val > ans) {
                            ans = val;
                        }
                        lastPop = s.pop();
                    } else if (topNode.h < h) {
                        s.push(new Node(lastPop.index, h));
                        break;
                    } else {
                        break;
                    }
                }
            }
        }

        return ans;
    }
    class Node {
        int index;
        int h;
        Node(int i, int h) {
            this.index = i;
            this.h = h;
        }
    }
}
