package elvis.leetcode;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++)
            for (int j = 0; j < text2.length(); j++) {
                if (dp[i - 1][j] < i - 1 && dp[i - 1][j] < j && text1.charAt(i) == text2.charAt(j))
                    dp[i][j] = dp[i - 1][j] + 1;
                else if (i > 0 && j > 0)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        return dp[text1.length() - 1][text2.length() - 1];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence l = new LongestCommonSubsequence();
        System.out.println(l.longestCommonSubsequence("abcde", "ace"));
    }
}
