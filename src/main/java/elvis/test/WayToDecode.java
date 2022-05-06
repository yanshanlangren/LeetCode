package elvis.test;

import java.util.Scanner;

public class WayToDecode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        int[] dp = new int[input.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= input.length(); i++) {
            if (input.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            } else if (input.charAt(i - 2) > '2' || input.charAt(i - 2) == '0') {
                System.out.println(0);
                return;
            }
            if (input.charAt(i - 2) < '2' || (input.charAt(i - 2) == '2' && input.charAt(i - 1) <= '6'))
                dp[i] += dp[i - 2];
        }
        System.out.println(dp[input.length()]);
    }
}
