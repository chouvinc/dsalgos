import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int[] solutions = new int[n+1];

        for (int i=1; i<=n; i++) {
            solutions[i] = solutions[i-1] + 1;
            if (i%2 == 0) {
                solutions[i] = Math.min(solutions[i/2] + 1, solutions[i]);
            }
            if (i%3 == 0) {
                solutions[i] = Math.min(solutions[i/3] + 1, solutions[i]);
            }
        }

        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0 && (solutions[n/3] == solutions[n] - 1)) {
                n /= 3;
            } else if (n % 2 == 0 && (solutions[n/2] == solutions[n] - 1)) {
                n /= 2;
            } else {
                n -= 1;
            }
        }

        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

