package pepcoding;

public class CountPathStairs {
    public static void main(String[] args) {
        System.out.println(countPathWithoutDp(4));
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
}
