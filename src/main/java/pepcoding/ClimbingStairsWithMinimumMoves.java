package pepcoding;

import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class ClimbingStairsWithMinimumMoves {
    public static void main(String[] args) {
        int stairToClimb = 45;
        //int[] jumpArray = {3, 2, 4, 2, 0, 2, 3, 1, 2, 2};
        int[] jumpArray = new int[stairToClimb];
        for(int i = 0; i < jumpArray.length; i++) {
            jumpArray[i] = ThreadLocalRandom.current().nextInt(1, 4);
        }

        Arrays.stream(jumpArray).forEach(value -> System.out.printf("%d, ", value));
        System.out.println();

        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        System.out.println(minimumMovesWithTabulation(jumpArray, stairToClimb));
        System.out.println("With Tabulation TooK::" + stopwatch.stop());

        stopwatch.reset().start();
        System.out.println(minimumMovesWithMemoization(jumpArray, 0, stairToClimb, new Integer[stairToClimb + 1]));
        System.out.println("With Memoization Took:: " + stopwatch.stop());

        stopwatch.reset().start();
        System.out.println(minimumMovesWithoutDp(jumpArray, 0, stairToClimb));
        System.out.println("Without DP Took:: " + stopwatch.stop());
    }

    public static Integer minimumMovesWithTabulation(int[] jumpArray, int stairToClimb) {
        Integer[] tabulationArray = new Integer[stairToClimb + 1];
        tabulationArray[stairToClimb] = 0;

        for(int i = jumpArray.length - 1; i >= 0; i--) {
            Integer tempMin = null;
            for(int j = jumpArray[i]; j > 0; j--) {
                if(i + j < tabulationArray.length && tabulationArray[i + j] != null) {
                    if (tempMin == null)
                        tempMin = 1 + tabulationArray[i + j];
                    else {
                        if(1 + tabulationArray[i + j] < tempMin)
                            tempMin = 1 + tabulationArray[i + j];
                    }
                }
            }
            tabulationArray[i] = tempMin;
        }
        return tabulationArray[0];
    }

    public static Integer minimumMovesWithMemoization(int[] jumpArray, int currentStair,
                                                  int stairToClimb, Integer[] memoizationArray) {
        if(currentStair == stairToClimb) {
            memoizationArray[stairToClimb] = 0;
            return memoizationArray[stairToClimb];
        } else if(currentStair > stairToClimb) {
            return null;
        }

        if(memoizationArray[currentStair] != null)
            return memoizationArray[currentStair];

        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int i = 1; i <= jumpArray[currentStair]; i++) {
            Integer minPath = minimumMovesWithMemoization(jumpArray, currentStair + i, stairToClimb, memoizationArray);
            if(minPath != null)
                treeSet.add(minPath + 1);
        }
        memoizationArray[currentStair] = treeSet.size() > 0 ? treeSet.first() : null;
        return memoizationArray[currentStair];
    }

    public static Integer minimumMovesWithoutDp(int[] jumpArray, int currentStair, int stairToClimb) {
        if(currentStair == stairToClimb) {
            return 0;
        } else if(currentStair > stairToClimb) {
            return null;
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int i = 1; i <= jumpArray[currentStair]; i++) {
            Integer minPath = minimumMovesWithoutDp(jumpArray, currentStair + i, stairToClimb);
            if(minPath != null)
                treeSet.add(minPath + 1);
        }
        return treeSet.size() > 0 ? treeSet.first() : null;
    }
}
