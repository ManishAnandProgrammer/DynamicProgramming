package pepcoding;

import com.google.common.base.Stopwatch;

import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class PathWithMaximumGold {
    public static void main(String[] args) {
        int[][] matrix = getMatrix();
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        System.out.println(maxGoldCaller(matrix, 0, 0, 14, ""));
        System.out.println("Without Dp Took:: " + stopwatch.stop());

        stopwatch.reset().start();
        System.out.println(maxGoldByTabulationDp(matrix));
        System.out.println("With Tabulation DP Took:: "+stopwatch.stop());
    }

    private static int[][] getMatrix() {
        int[][] matrix = new int[15][15];

        for(int row = 0; row < matrix.length; row++) {
            for(int column = 0; column < matrix.length; column++) {
                matrix[row][column] = ThreadLocalRandom.current().nextInt(0, 10);
            }
        }
        printMatrix(matrix);
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for(int row = 0; row < matrix.length; row++) {
            for(int column = 0; column < matrix.length; column++) {
                System.out.printf("%d ", matrix[row][column]);
            }
            System.out.println();
        }
    }

    private static Integer maxGoldCaller(int[][] matrix, int currentRow, int currentColumn, int destinationColumn, String pathSoFar) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int i = 0; i < matrix.length; i++) {
            Integer max = maxGold(matrix, i, currentColumn, destinationColumn, pathSoFar);
            if(max != null)
                treeSet.add(max);
        }

        return treeSet.size() > 0 ? treeSet.last() : null;
    }

    private static Integer maxGold(int[][] matrix, int currentRow, int currentColumn, int destinationColumn, String pathSoFar) {
        if(currentRow < 0 || currentRow >= matrix.length) {
            return null;
        }

        if(currentColumn == destinationColumn) {
            //System.out.println(pathSoFar);
            return matrix[currentRow][currentColumn];
        }

        Integer diagonalUp = maxGold(matrix, currentRow - 1, currentColumn + 1, destinationColumn, pathSoFar + "U");
        Integer straightForward = maxGold(matrix, currentRow, currentColumn + 1, destinationColumn, pathSoFar + "F");
        Integer diagonalDown = maxGold(matrix, currentRow + 1, currentColumn + 1, destinationColumn, pathSoFar + "D");

        TreeSet<Integer> treeSet = new TreeSet<>();
        if(diagonalUp != null)
            treeSet.add(diagonalUp + matrix[currentRow][currentColumn]);

        if(straightForward != null)
            treeSet.add(straightForward + matrix[currentRow][currentColumn]);

        if(diagonalDown != null)
            treeSet.add(diagonalDown + matrix[currentRow][currentColumn]);

        return treeSet.size() > 0 ? treeSet.last() : null;
    }

    private static int maxGoldByTabulationDp(int[][] matrix) {
        int[][] dpArray = new int[matrix.length][matrix[0].length];

        for(int column = matrix[0].length - 1; column >= 0; column--) {
            for(int row = 0; row < matrix.length; row++) {
                if(column == matrix[0].length - 1) {
                    dpArray[row][column] = matrix[row][column];
                } else if(row == 0) {
                    dpArray[row][column] = matrix[row][column] + Math.max(dpArray[row][column + 1], dpArray[row + 1][column + 1]);
                } else if(row == matrix.length - 1) {
                    dpArray[row][column] = matrix[row][column] + Math.max(dpArray[row][column + 1], dpArray[row - 1][column + 1]);
                } else {
                    dpArray[row][column] = matrix[row][column] + Math.max(dpArray[row][column + 1],
                            Math.max(dpArray[row + 1][column + 1], dpArray[row - 1][column + 1]));
                }
            }
        }
        int result = 0;
        for(int row = 0; row < matrix.length; row++) {
            if(dpArray[row][0] > result) {
                result = dpArray[row][0];
            }
        }

        return result;
    }
}
