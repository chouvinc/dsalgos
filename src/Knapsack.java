import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int[] optimalArray = new int[W+1];

        for (int i=0; i<optimalArray.length; i++) {
            optimalArray[i] = 0;
        }

        // sort from smallest to largest gold bars
        Arrays.sort(w);

        for (int i=1; i<optimalArray.length; i++) {
            optimalArray[i] = optimalArray[i-1];

            // gets the largest value in there
            for (int j=w.length-1; j>=0; j--) {
                if (w[j] <= i) {
                    if (optimalArray[i-w[j]] + w[j] <= i) {
                        optimalArray[i] = optimalArray[i-w[j]] + w[j];
                    }
                }
            }

        }


        System.out.println(Arrays.toString(optimalArray));

        return optimalArray[optimalArray.length-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}
