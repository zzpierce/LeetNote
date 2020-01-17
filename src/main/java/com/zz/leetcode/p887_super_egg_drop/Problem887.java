package com.zz.leetcode.p887_super_egg_drop;

/**
 * You are given K eggs, and you have access to a building with N floors from 1 to N.
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.
 * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
 * Your goal is to know with certainty what the value of F is.
 * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
 */
public class Problem887 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.superEggDrop(2, 1));
    }
}

/**
 * [9.0]
 * 思路分析
 * 构造状态转移方程
 * 非常天才的思路，使用步数而不是层数进行DP，累死我也想不出来
 * F(E,M)表示E个蛋，M步，可以确认的最大层数
 * 用一个蛋实验某个楼层K，会有两种情况，碎了或者没碎
 * 如果碎了，我们可以用E-1个蛋实验K以下的F(E-1, M-1)个楼层
 * 如果没碎，我们可以用E个蛋实验K以上的F(E, M-1)个楼层
 * 因此F(E,M) = F(E-1, M-1) + F(E, M-1) + 1
 */
class Solution {
    public int superEggDrop(int K, int N) {
        int[] v = new int[N > K ? N + 1 : K + 1];
        int step = 0;
        while(v[K] < N) {
            for (int j = K; j > 0; j --) {
                v[j] = v[j] + v[j-1] + 1;
            }
            step ++;
        }
        return step;
    }
}
