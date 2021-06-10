package pepcoding;

import com.google.common.base.Stopwatch;

public class ZeroOneKnapsack {
    public static void main(String[] args) {
        int[] wightArray = {2, 5, 1, 3, 4};
        int[] priceArray = {15, 14, 10, 45, 30};

        int maxWeigh = 11;

        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        int resultFromDp = knapsackWithDp(wightArray, priceArray, maxWeigh);
        System.out.println(resultFromDp);
        System.out.println("Knapsack with DP Took:: " + stopwatch.stop());
    }

    public static int knapsackWithDp(int[] weightArray, int[] priceArray, int maxWeigh) {
        int[][] dp = new int[weightArray.length + 1][maxWeigh + 1];

        for(int row = 1; row < dp.length; row++) {
            for(int column = 1; column < dp[row].length; column++) {
                int tryToAddWeight = weightArray[row - 1];
                if(column >= tryToAddWeight) {
                    int remainingWeightAfterAddingWeight = column - tryToAddWeight;
                    int priceIfWeAddElement = priceArray[row - 1] + dp[row - 1][remainingWeightAfterAddingWeight];
                    int priceIfWeDoNotAddElement = dp[row - 1][column];

                    dp[row][column] = Math.max(priceIfWeAddElement, priceIfWeDoNotAddElement);
                } else {
                    dp[row][column] = dp[row - 1][column];
                }
            }
//            Arrays.stream(dp[row]).forEach(e -> System.out.print(e + " "));
//            System.out.println();
        }
        return dp[weightArray.length][maxWeigh];
    }
}
