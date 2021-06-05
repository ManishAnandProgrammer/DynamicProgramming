package pepcoding;

import com.google.common.base.Stopwatch;

public class CoinChangePermutation {
    public static void main(String[] args) {
        int[] coins = {2, 3, 5, 6};
        int targetSum = 55;
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        printNumberOfPermutation(coins, targetSum);
        System.out.println("Permutation of Coins With DP Took:: " + stopwatch.stop());

        stopwatch.reset().start();
        System.out.println(permutationOfCoinsWithoutDP(coins, targetSum));
        System.out.println("Permutation of Coins Without DP Took:: " + stopwatch.stop());
    }

    public static void printNumberOfPermutation(int[] coins, int targetSum) {
        int[] dp = new int[targetSum + 1];
        dp[0] = 1;

        for(int amount = 1; amount < dp.length; amount++) {
            for(int coin: coins) {
                if(coin <= amount) {
                    int remainingAmount = amount - coin;
                    dp[amount] += dp[remainingAmount];
                }
            }
        }

        System.out.println(dp[targetSum]);
    }

    public static int permutationOfCoinsWithoutDP(int[] coins, int targetSum) {
        if(targetSum == 0) {
            return 1;
        }

        int totalWays = 0;
        for(int coin : coins) {
            if(coin <= targetSum)
                totalWays += permutationOfCoinsWithoutDP(coins, targetSum - coin);
        }
        return totalWays;
    }
}
