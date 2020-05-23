package com.zz.leetcode.problem.p458_poor_pigs;

/**
 * There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water. They all look identical.
 * If a pig drinks the poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?
 *
 * Answer this question, and write an algorithm for the general case.
 *
 *  
 *
 * General case:
 *
 * If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the poisonous bucket within p minutes? There is exactly one bucket with poison.
 *
 *  
 *
 * Note:
 *
 * A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.
 * After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes. During this time, only observation is allowed and no feedings at all.
 * Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
 *
 */
public class Problem458 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.poorPigs(1000,15,  60));
    }
}

/**
 * [8.0]
 * 耗费脑细胞警告
 * 错误想法事例
 * N头猪 1次 N+1桶
 * N头猪 2次 第一次每个喝N桶 剩N+1桶 => N-1猪N桶或N猪N+1桶 N*N+(N+1)桶
 * N头猪 3次 N((N-1)*N+1) + N(N+1)+1 = N(N*N-N+1) + N*N+N+1 = N*N*N - N*N + N + N*N + N + 1 = N*N*N + 2N + 1
 * 正确思路
 * 8桶水是可以3头猪一次测出的
 * 1 1 1
 * 1 1 0
 * 1 0 1
 * 1 0 0
 * 0 1 1
 * 0 1 0
 * 0 0 1
 * 0 0 0
 * 需要构造这种喝水情况，结果有8种，即可测出哪一桶有毒
 * 上述情况是猪有两种状态 活和第一轮死
 * 如果有两轮，猪就有三种状态，活、第一轮死和第二轮死
 * 此时3头猪可以构造3的3次方=27种状态
 */
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int round = minutesToTest / minutesToDie + 1;
        double p = Math.log(buckets) / Math.log(round);
        double c = Math.ceil(p);
        return (int)c;
    }

}

