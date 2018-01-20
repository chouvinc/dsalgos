import java.util.*;

public class NegativeCycle {
    private static int[] dist;
    private static boolean relaxed;

    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        int v = adj.length;
        dist = new int[v];

        for (int i=1; i<v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i=0; i<v-1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = 0; // set new starting node
            }

            for (int j=0; j<adj.length; j++) {
                for (int k=0; k<adj[j].size(); k++) {
                    relax(j, adj[j].get(k), cost[j].get(k));
//                    System.out.println(Arrays.toString(dist));
                }
            }
        }

        relaxed = false;
        for (int j=0; j<adj.length; j++) {
            for (int k=0; k<adj[j].size(); k++) {
                relax(j, adj[j].get(k), cost[j].get(k));
            }
        }

        if (!relaxed) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void relax(int curr, int neighbor, int weight) {
        int oldDist = dist[neighbor];
        int newDist = dist[curr] + weight;

        // means integer overflow, return
        if (dist[curr] == Integer.MAX_VALUE /*&& newDist < 0*/) return;

        if (oldDist > newDist) {
            dist[neighbor] = newDist;
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
        System.out.println(negativeCycle(adj, cost));
    }
}

