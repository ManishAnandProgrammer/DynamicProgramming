package pepcoding;

import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class MinimumCostPathInMatrix {
    public static void main(String[] args) {
        int[][] matrix = getMatrix();
        int result = minimumCostWithoutDp(matrix, 0, 0, 4, 4, "");
        System.out.println("The Result :: " + result);
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
            System.out.println(pathSoFar);
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
}
