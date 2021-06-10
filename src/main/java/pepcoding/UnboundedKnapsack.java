package pepcoding;

import com.google.common.base.Stopwatch;

public class UnboundedKnapsack {
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

    private static int knapsackWithDp(int[] weightArray, int[] priceArray, int maxWeigh) {
        int[] dp = new int[maxWeigh + 1];

        for(int indexRepresentingWeighInDp = 1; indexRepresentingWeighInDp < dp.length; indexRepresentingWeighInDp++) {
            int maxWeightAtThisPoint = 0;
            for(int j = 0; j < weightArray.length; j++) {
                int itemWeight = weightArray[j];
                if(itemWeight <= indexRepresentingWeighInDp) {
                    int spaceRemaining = indexRepresentingWeighInDp - itemWeight;
                    int priceAtThisPoint = priceArray[j] + dp[spaceRemaining];

                    maxWeightAtThisPoint = Math.max(priceAtThisPoint, maxWeightAtThisPoint);
                }
            }
            dp[indexRepresentingWeighInDp] = maxWeightAtThisPoint;
        }
        return dp[maxWeigh];
    }
}
