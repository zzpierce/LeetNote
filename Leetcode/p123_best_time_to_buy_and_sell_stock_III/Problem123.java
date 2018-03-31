package p123_best_time_to_buy_and_sell_stock_III;

/**
 * @author pierce
 */
public class Problem123 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] profit = new int[len + 1];
        int ma = 0, nowMin = Integer.MAX_VALUE, potentialMin = Integer.MAX_VALUE;
        for (int i = 0; i < len; i ++) {
            int d = prices[i];
            if (d < potentialMin) {
                potentialMin = d;
            }
            if (d - potentialMin > ma - nowMin) {
                ma = d;
                nowMin = potentialMin;
            }
            profit[i + 1] = ma - nowMin;
        }
        //printArray(profit);

        ma = 0; nowMin = Integer.MAX_VALUE;
        int potentialMax = 0;
        for (int i = len - 1; i >= 0; i --) {
            int d = prices[i];
            if (d > potentialMax) potentialMax = d;
            if (potentialMax - d > ma - nowMin) {
                ma = potentialMax;
                nowMin = d;
            }
            profit[i] += (ma - nowMin);
        }
        //printArray(profit);

        int ans = 0;
        for (int i = 0; i < len + 1; i ++) {
            if (profit[i] > ans) {
                ans = profit[i];
            }
        }
        return ans;
    }
}
