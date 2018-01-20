import java.util.*;

public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
        DisjointSet ds = new DisjointSet(x.length);

        // initialize priority queue
        // priority queue has comparator based on point distances

        Queue<PointPairs> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.distance));

        // generate all possible edges and put into pq
        for (int i=0; i<x.length; i++) {
            for (int j=0; j<x.length; j++) {
                if (i == j) {
                    continue;
                }

                pq.add(new PointPairs(new Point(x[i], y[i], i), new Point (x[j], y[j], j)));
            }
        }

        // get minimum distance from pq...
        while (!pq.isEmpty()) {
            PointPairs min = pq.poll();

            if (ds.kSubset(k)) {
                break;
            }

            if (!(ds.find(min.p1.id) == ds.find(min.p2.id))) {
                ds.union(min.p1.id, min.p2.id);
            }
        }

        double minDist = Double.MAX_VALUE;

        while (!pq.isEmpty()) {
            PointPairs min = pq.poll();

            if (!(ds.find(min.p1.id) == ds.find(min.p2.id))) {
                minDist = Math.min(minDist, min.distance);
            }
        }

        return (double) Math.round(minDist * 10000000d) / 10000000d;
    }

    static class DisjointSet {
        int[] parent, rank;
        int n;
        Set<Integer> tracker;

        public DisjointSet(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.n = n;
            tracker = new HashSet();

            for (int i=0; i<n; i++) {
                parent[i] = i;
                tracker.add(i);
            }
        }

        int find(int x) {

            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        void union(int x, int z) {
            int root1 = find(x);
            int root2 = find(z);

            if (root1 == root2) return;

            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
                tracker.remove(root1);
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
                tracker.remove(root2);
            } else {
                parent[root1] = root2;
                tracker.remove(root1);
                rank[root2] ++;
            }
        }

        boolean kSubset(int k) {
            return k == tracker.size();
        }
    }



    static class PointPairs {
        Point p1;
        Point p2;
        double distance;

        public PointPairs(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;

            this.distance = distance(p1.x, p2.x, p1.y, p2.y);
        }
    }

    static class Point {
        int x;
        int y;
        int id;

        public Point(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    private static double distance(int x1, int x2, int y1, int y2) {
        double distance = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
        return distance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

