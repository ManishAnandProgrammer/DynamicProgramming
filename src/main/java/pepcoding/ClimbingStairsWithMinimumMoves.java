package pepcoding;

public class ClimbingStairsWithMinimumMoves {
    public static void main(String[] args) {
        int stairToClimb = 10;
        int[] jumpArray = {3, 2, 4, 2, 0, 2, 3, 1, 2, 2};

        System.out.println(minimumMovesWithTabulation(jumpArray, stairToClimb));
    }

    public static int minimumMovesWithTabulation(int[] jumpArray, int stairToClimb) {
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

}
