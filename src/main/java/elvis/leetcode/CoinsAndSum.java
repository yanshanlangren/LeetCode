package elvis.leetcode;

import java.util.Arrays;

public class CoinsAndSum {
    public int coinsAndSum(int sum, int[] coins) {
        Arrays.sort(coins);
        //dp[i][j] 表示只使用i种硬币能表示j的方法
        int[][] dp = new int[coins.length + 1][sum + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= sum; j++) {
                for (int k = 0; k * coins[i - 1] <= j; k++)
                    dp[i][j] += dp[i - 1][j - k * coins[i - 1]];
            }
        }
        return dp[coins.length][sum];
    }

    public int coinsAndSum_Other(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i - 1])
                    //如果j>=coins[i-1], dp[i][j] = 没算第i-1种硬币的结果+已经算了至少一次第i-1种之后的结果
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinsAndSum c = new CoinsAndSum();
        System.out.println(c.coinsAndSum_Other(10, new int[]{1, 2, 5}));
//        System.out.println(c.coinsAndSum(10, new int[]{10}));
    }
}
