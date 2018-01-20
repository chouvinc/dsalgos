import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        // write your code here
        // sort values and weights from smallest to largest

        double[] copy = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            copy[i] = (double)values[i]/(double)weights[i];
        }

        Arrays.sort(copy);
        int copyIndex = copy.length-1;

        while (capacity > 0) {
            for (int i = 0; i < values.length; i++) {
                if (copyIndex < 0) {
                    return round(value, 4);
                }

                // find the item in the values array
                if ((double)values[i]/(double)weights[i] == copy[copyIndex]) {
                    // if we can include the entire value
                    if (capacity >= weights[i]) {
                        value += values[i];
                        capacity -= weights[i];
                    } else { // otherwise include a fraction of the value
                        value += (double) values[i]/(double) weights[i] * (double) capacity;
                        capacity = 0;
                    }
                }
            }

            copyIndex --;
        }

        return round(value, 4);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

