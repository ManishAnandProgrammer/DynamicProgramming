package pepcoding;

public class CoinChangePermutation {
    public static void main(String[] args) {
        int[] coins = {2, 3, 5, 6};
        int targetSum = 10;

        printNumberOfPermutation(coins, targetSum);
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
}
