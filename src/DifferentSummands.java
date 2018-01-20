import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();

        int counter = 1;

        while (n > 0) {
            if (n - counter >= counter + 1) {
                summands.add(counter);
                n -= counter;
                counter ++;
            } else {
                break;
            }
        }

        summands.add(n);

        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}