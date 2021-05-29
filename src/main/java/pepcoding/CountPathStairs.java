package pepcoding;

import com.google.common.base.Stopwatch;

public class CountPathStairs {
    public static void main(String[] args) {
        int numberOfStairs = 33;
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        System.out.println(countPathWithoutDp(numberOfStairs));
        System.out.println("Count Path Without Dp Took:: " + stopwatch.stop());

        stopwatch.reset().start();
        System.out.println(countPathWithMemoization(numberOfStairs, new int[numberOfStairs + 1]));
        System.out.println("Count Path With Memoization Took:: " + stopwatch.stop());

        stopwatch.reset().start();
        System.out.println(countPathWithTabulation(numberOfStairs));
        System.out.println("Count Path With Tabulation Took:: " + stopwatch.stop());
    }
    
    private static int countPathWithoutDp(int stairNumber) {

        if(stairNumber == 0) {
            return 1;
        } else if(stairNumber < 0) {
            return 0;
        }

        int numberOfPathsByClimbingDownOneStep = countPathWithoutDp(stairNumber - 1);
        int numberOfPathsByClimbingDownTwoSteps = countPathWithoutDp(stairNumber - 2);
        int numberOfPathsByClimbingDownThreeSteps = countPathWithoutDp(stairNumber - 3);

        int totalPaths = numberOfPathsByClimbingDownOneStep +
                numberOfPathsByClimbingDownTwoSteps +
                numberOfPathsByClimbingDownThreeSteps;

        return totalPaths;
    }

    private static int countPathWithMemoization(int stairNumber, int[] memoizationArray) {

        if(stairNumber == 0) {
            return 1;
        } else if(stairNumber < 0) {
            return 0;
        }

        if(memoizationArray[stairNumber] > 0) {
            return memoizationArray[stairNumber];
        }

        int numberOfPathsByClimbingDownOneStep = countPathWithMemoization(stairNumber - 1, memoizationArray);
        int numberOfPathsByClimbingDownTwoSteps = countPathWithMemoization(stairNumber - 2, memoizationArray);
        int numberOfPathsByClimbingDownThreeSteps = countPathWithMemoization(stairNumber - 3, memoizationArray);

        int totalPaths = numberOfPathsByClimbingDownOneStep +
                numberOfPathsByClimbingDownTwoSteps +
                numberOfPathsByClimbingDownThreeSteps;

        memoizationArray[stairNumber] = totalPaths;
        return totalPaths;
    }

    public static int countPathWithTabulation(int number) {
        int[] array = new int[number + 1];

        array[0] = 1;
        for(int i = 1; i < array.length; i++) {
            if(i == 1) {
                array[i] = array[i - 1];
            } else if(i == 2) {
                array[i] = array[i - 1] + array[i - 2];
            } else {
                array[i] = array[i - 1] + array[i - 2] + array[i - 3];
            }
        }

        return array[number];
    }
}
