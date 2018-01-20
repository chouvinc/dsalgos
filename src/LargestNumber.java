import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
        List<String> asList = new ArrayList<>(a.length);
        for (int i = 0; i < a.length; i++) {
            asList.add(a[i]);
        }

        Collections.sort(asList);
        Collections.reverse(asList);
        String result = "";

        int lessThan = 10;

        while (asList.size() > 0) {
            for (int i =0; i<asList.size(); i++) {
                if (Integer.parseInt(asList.get(i)) < lessThan) {
                    result += asList.get(i);
                    asList.remove(i);
                    i--;
                }
            }
            lessThan *= 10;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}



