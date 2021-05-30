package pepcoding;

import java.util.Arrays;

public class CountPathWithVariableNumberJumps {
    public static void main(String[] args) {
        int[] jumpArray = {3, 3, 0, 2, 2, 3};
        int stairNumberToClimb = 6;
        System.out.println(numberOfPathUsingTabulation(jumpArray, stairNumberToClimb));
    }

    public static int numberOfPathUsingTabulation(int[] jumpArray, int stairNumberToClimb) {
        int[] tabulationArray = new int[stairNumberToClimb + 1];
        tabulationArray[stairNumberToClimb] = 1;

        for(int i = jumpArray.length - 1; i >= 0; i--) {
            for(int j = jumpArray[i]; j > 0; j--) {
                if(i + j < tabulationArray.length) {
                    tabulationArray[i] += tabulationArray[i + j];
                }
            }
        }
        Arrays.stream(tabulationArray).forEach(value -> System.out.printf("%d, ", value));
        System.out.println();
        return tabulationArray[0];
    }

}
