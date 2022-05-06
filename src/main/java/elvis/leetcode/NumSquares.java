package elvis.leetcode;

public class NumSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++)
                min = Math.min(dp[i - j * j] + 1, min);
            dp[i] = min;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        NumSquares n = new NumSquares();
        System.out.println(n.numSquares(13));
    }
}
