package elvis.leetcode;

import java.util.PriorityQueue;

public class MaxProfit {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, min_i = 0, max_i = 0;
        int[] dp = new int[n + 1];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (min > prices[i]) {
                min = prices[i];
                min_i = i;
            }
            if (i > min_i && max < prices[i]) {
                max = prices[i];
                max_i = i;
            }
            if (max - min - 1 + dp[min_i] > dp[i]) {
                dp[i] = max - min - 2 + dp[min_i];
                min = Integer.MAX_VALUE;
            }

        }
        return dp[n];
    }

    public static void main(String[] args) {
        MaxProfit mp = new MaxProfit();
        System.out.println(mp.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }
}
