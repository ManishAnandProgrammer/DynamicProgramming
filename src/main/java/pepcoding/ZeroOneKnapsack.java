package pepcoding;

public class ZeroOneKnapsack {
    public static void main(String[] args) {
        int[] wightArray = {2, 5, 1, 3, 4};
        int[] priceArray = {15, 14, 10, 45, 30};

        int maxWeigh = 7;

        int result = knapsackWithoutDp(wightArray, priceArray, maxWeigh, 0, 0);
        System.out.println(result);
    }

    public static int knapsackWithoutDp(int[] weightArray, int[] priceArray, int maxWeigh, int maxPrice, int currentItemIndex) {
        if(maxWeigh == 0) {
            return maxPrice;
        }

        int finalResult = 0;
        for(int i = currentItemIndex; i < priceArray.length; i++) {
            if(maxWeigh >= weightArray[i]) {
                int newWeight = maxWeigh - weightArray[i];
                int newMaxPrice = maxPrice + priceArray[i];
                int subPrice = knapsackWithoutDp(weightArray, priceArray, newWeight, newMaxPrice, i + 1);
                if(subPrice > finalResult) {
                    finalResult = subPrice;
                }
            }
        }
        return finalResult;
    }
}
