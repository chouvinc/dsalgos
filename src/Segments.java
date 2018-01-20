import java.util.Arrays;
import java.util.Scanner;

public class Segments {

    // solution: indices that have the largest multiple are the points we want to return

    private static int[] optimalPoints(Segment[] segments){
        // sort segments by segment start
        Arrays.sort(segments, (a, b) -> a.end - b.end);

        //write your code here
        int[] points = new int[2 * segments.length];

        for (int i = 0; i < segments.length; i++) {
            int toSkip = 0;
            points[i] = segments[i].end;

            // if the point is between the next n line segments inclusive, skip the next n segment
            while (i+1+toSkip < segments.length) {
                if (!(points[i] >= segments[i+1+toSkip].start && points[i] <= segments[i+1+toSkip].end)) {
                    break;
                }
                toSkip++;
            }

            i += toSkip;
        }
        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);

        String printString = "";
        int numPoints = 0;

        for (int point: points) {
            if (point != 0) {
                printString += point + " ";
                numPoints ++;
            }
        }

        System.out.println(numPoints);
        System.out.println(printString);
    }
}
 