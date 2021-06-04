package pepcoding;

import java.util.Arrays;

public class CoinChangeCombinations {
    public static void main(String[] args) {
        int[] coins = {2, 3, 5};
        int needToMatchCoinValue = 7;
        coinChangeCombo(coins, needToMatchCoinValue);
    }

    public static void coinChangeCombo(int[] coins, int needToMatchCoinValue) {
        int[] dp = new int[needToMatchCoinValue + 1];
        dp[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            int coinValue = coins[i];
            for(int j = coinValue; j < dp.length; j++) {
                dp[j] += dp[j - coinValue];
            }
        }
        System.out.println(dp[needToMatchCoinValue]);
    }
}
