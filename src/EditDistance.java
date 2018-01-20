import java.util.*;

class EditDistance {
    public static int EditDistance(String s, String t) {
        // write your code here
        int lengths = s.length();
        int lengtht = t.length();

        int[][] x = new int[lengths+1][lengtht+1];

        // initialize the first column and row
        for (int i=0; i<= lengths; i++) {
            x[i][0] = i;
        }

        for (int i=0; i<= lengtht; i++) {
            x[0][i] = i;
        }

        // compare two strings
        for (int i=0; i<lengths; i++) {
            char a = s.charAt(i);
            for (int j=0; j<lengtht; j++) {
                char b = t.charAt(j);

                if (a == b) {
                    // if they're equal we travelled diagonally & there's no incrementing
                    x[i+1][j+1] = x[i][j];
                } else {
                    // if they're not equal:
                    // we travel diagonally
                    int sub = x[i][j] + 1;
                    // we travel horizontally
                    int ins = x[i][j+1] + 1;
                    // we travel vertically
                    int del = x[i+1][j] + 1;

                    int min = Math.min(sub, ins);
                    min = Math.min(min, del);
                    x[i+1][j+1] = min;
                }
            }
        }

        return x[lengths][lengtht];
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
