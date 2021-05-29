package pepcoding;

import com.google.common.base.Stopwatch;

public class Fibonacci {
    public static void main(String[] args) {
        int inputNumber = 45;
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(fibonacciWithoutDp(inputNumber));
        System.out.println("Without DP Method Took:: " + stopwatch.stop());

        stopwatch.reset().start();
        System.out.println(fibonacciWithDp(inputNumber, new int[inputNumber+1]));
        System.out.println("WithDp Method Took:: " + stopwatch.stop());
    }

    private static int fibonacciWithoutDp(int number) {

        if(number == 0 || number == 1)
            return number;

        int numberMinusOneFibonacci = fibonacciWithoutDp(number - 1);
        int numberMinusTwoFibonacci = fibonacciWithoutDp(number - 2);

        int result = numberMinusOneFibonacci + numberMinusTwoFibonacci;

        return result;
    }

    private static int fibonacciWithDp(int number, int[] memoizationArray) {

        if(number == 0 || number == 1)
            return number;

        if(memoizationArray[number] != 0) {
            return memoizationArray[number];
        }

        int numberMinusOneFibonacci = fibonacciWithDp(number - 1, memoizationArray);
        int numberMinusTwoFibonacci = fibonacciWithDp(number - 2, memoizationArray);

        int result = numberMinusOneFibonacci + numberMinusTwoFibonacci;

        memoizationArray[number] = result;
        return result;
    }
}
