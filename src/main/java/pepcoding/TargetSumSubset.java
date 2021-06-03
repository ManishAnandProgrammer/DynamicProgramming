package pepcoding;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TargetSumSubset {
    public static void main(String[] args) {
        int[] targetArray = {4, 2, 3, 1, 7};
        int targetSum = 10;

        isTargetSumExist(targetArray, targetSum);
    }

    private static void isTargetSumExist(int[] targetArray, int targetSum) {
        boolean[][] dpArray = new boolean[targetArray.length + 1][targetSum + 1];

        for(int row = 0; row < dpArray.length; row++) {
            for(int column = 0; column < dpArray[row].length; column++) {
                if (row == 0 && column == 0) {
                    dpArray[row][column] = true;
                } else if (row == 0) {
                    dpArray[row][column] = false;
                } else if (column == 0) {
                    dpArray[row][column] = true;
                } else {
                    if (dpArray[row - 1][column]) {
                        dpArray[row][column] = true;
                    } else {
                        int value = targetArray[row - 1];
                        if(column >= value) {
                            if(dpArray[row - 1][column - value]) {
                                dpArray[row][column] = true;
                            }
                        }
                    }
                }

            }
        }
        //printDpArray(dpArray);
        System.out.println(dpArray[targetArray.length][targetSum]);
    }

    public static void printDpArray(boolean[][] dpArray) {
        for(int row = 0; row < dpArray.length; row++) {
            for(int column = 0; column < dpArray[row].length; column++) {
                System.out.print(dpArray[row][column] + " ");
            }
            System.out.println();
        }
    }
}
