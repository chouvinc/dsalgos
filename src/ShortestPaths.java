import java.util.*;

public class ShortestPaths {
    private static boolean relaxed;

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        //write your code here
        int v = adj.length;
        distance[s] = 0;
        Queue<Integer> neg = new LinkedList();

        // for all vertices-1 V-1
        for (int i=1; i<v; i++) {
            // for all edges
            for (int j=0; j<adj.length; j++) {
                for (int k=0; k<adj[j].size(); k++) {
                    relax(j, adj[j].get(k), cost[j].get(k), distance, reachable);
                }
            }
        }

        for (int j=0; j<adj.length; j++) {
            relaxed = false;
            for (int k=0; k<adj[j].size(); k++) {
                relax(j, adj[j].get(k), cost[j].get(k), distance, reachable);
            }

            if (relaxed) {
                neg.add(j);
            }
        }

        while (neg.size() > 0) {
            int node = neg.poll();
            shortest[node] = 0;

            for (int neighbor: adj[node])
                shortest[neighbor] = 0;

            if (shortest[reachable[node]] != 0)
                neg.add(reachable[node]);
        }
    }

    private static void relax(int curr, int neighbor, int weight, long[] dist, int[] reachable) {
        long oldDist = dist[neighbor];
        long newDist = dist[curr] + weight;

        // means integer overflow, return
        if (dist[curr] == Long.MAX_VALUE /*&& newDist < 0*/) return;

        if (oldDist > newDist) {
            dist[neighbor] = newDist;
            reachable[neighbor] = curr;
            relaxed = true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0 && distance[i] == Long.MAX_VALUE) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

