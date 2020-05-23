package com.zz.leetcode.problem.p847_shortest_path_visiting_all_nodes;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 *
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 *
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 * 示例 1：
 * 输入：[[1,2,3],[0],[0],[0]]
 * 输出：4
 * 解释：一个可能的路径为 [1,0,2,0,3]
 * 示例 2：
 * 输入：[[1],[0,2,4],[1,3,4],[2],[1,2]]
 * 输出：4
 * 解释：一个可能的路径为 [0,1,4,2,3]
 */
public class Problem847 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] m = new int[5][];
        m[0] = new int[]{1};
        m[1] = new int[]{0,2,4};
        m[2] = new int[]{1,3,4};
        m[3] = new int[]{2};
        m[4] = new int[]{1,2};
        int r = s.shortestPathLength(m);
        System.out.println(r);

    }
}

/**
 * [6.0]
 * 广度遍历 + 位运算，空间换时间
 * 由于数据小，可以将所有的可能性全部记录下来
 * r 记录路径包含的节点
 * p 记录当前节点
 * m[1 << size][size]足够记录所有的情况
 */
class Solution {
    public int shortestPathLength(int[][] graph) {
        int size = graph.length;
        Queue<State> queue = new LinkedBlockingQueue<>();
        int[][] m = new int[1 << size][size];
        for (int i = 0; i < size; i ++) {
            queue.add(new State(1 << i, i));
            m[1 << i][i] = 1;
        }
        while(true) {
            State s = queue.poll();
            if (s.r == (1 << size) - 1) {
                return m[s.r][s.p] - 1;
            }
            for (int j = 0; j < graph[s.p].length; j ++) {
                int next = graph[s.p][j];
                int r = s.r | (1 << next);
                if (m[r][next] == 0) {
                    m[r][next] = m[s.r][s.p] + 1;
                    queue.add(new State(r, next));
                }
            }
        }
    }

    static class State {
        State(int r, int p) {
            this.r = r;
            this.p = p;
        }
        int r;
        int p;
    }
}