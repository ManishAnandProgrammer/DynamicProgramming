package pepcoding;

import com.google.common.base.Stopwatch;

import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class MinimumCostPathInMatrix {
    public static void main(String[] args) {
        int[][] matrix = getMatrix();

        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        System.out.println(minimumCostWithoutDp(matrix, 0, 0,
                4, 4, ""));
        System.out.println("Result Without DP Took:: " + stopwatch.stop());

        stopwatch.reset().start();
        System.out.println(minimumCostWithTabulation(matrix));
        System.out.println("Result With Tabulation DP Took:: " + stopwatch.stop());
    }

    private static int[][] getMatrix() {
        int[][] matrix = new int[5][5];

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

    private static Integer minimumCostWithoutDp(int[][] matrix, int currentRow, int currentColumn,
                                            int destinationRow, int destinationColumn, String pathSoFar) {
        if(currentRow == destinationRow && currentColumn == destinationColumn) {
            //System.out.println(pathSoFar);
            return matrix[currentRow][currentColumn];
        } else if(currentRow >= matrix.length || currentColumn >= matrix.length) {
            return null;
        }

        Integer horizontalMoveCost = minimumCostWithoutDp(matrix, currentRow, currentColumn + 1,
                destinationRow, destinationColumn, pathSoFar + "r");
        Integer verticalMoveCost = minimumCostWithoutDp(matrix, currentRow + 1, currentColumn,
                destinationRow, destinationColumn, pathSoFar + "d");

        Integer currentIndexCost = matrix[currentRow][currentColumn];
        TreeSet<Integer> treeSet = new TreeSet<>();
        if(horizontalMoveCost != null)
            treeSet.add(horizontalMoveCost + currentIndexCost);

        if(verticalMoveCost != null)
            treeSet.add(verticalMoveCost + currentIndexCost);

        return treeSet.size() > 0 ? treeSet.first() : null;
    }

    private static Integer minimumCostWithTabulation(int[][] matrix) {
        int[][] dpArray = new int[matrix.length][matrix.length];

        for(int row = dpArray.length - 1; row >= 0; row--) {
            for(int column = dpArray[0].length-1; column >= 0; column--) {
                if(row == dpArray.length-1 && column == dpArray[0].length-1) {
                    dpArray[row][column] = matrix[row][column];
                } else if(row == dpArray.length - 1) {
                    dpArray[row][column] = matrix[row][column] + dpArray[row][column+1];
                } else if(column == dpArray.length - 1) {
                    dpArray[row][column] = matrix[row][column] + dpArray[row+1][column];
                } else {
                    dpArray[row][column] = Math.min(dpArray[row][column+1], dpArray[row+1][column]) + matrix[row][column];
                }
            }
        }

        return dpArray[0][0];
    }
}
