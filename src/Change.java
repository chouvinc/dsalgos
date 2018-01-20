import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        //write your code here
        int valueLeft = m;
        int numCoins = 0;

        while (valueLeft >= 10) {
            numCoins ++;
            valueLeft -= 10;
        }

        while (valueLeft >= 5) {
            numCoins ++;
            valueLeft -= 5;
        }

        while (valueLeft > 0) {
            numCoins ++;
            valueLeft --;
        }

        return numCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

// integer value to coins of value 1, 5, and 10
// input: 1 integer
// output: min number of coins of 1, 5, and 10

// safe move: fill the most out of the most valuable coin (10) and then move down w/ remainder