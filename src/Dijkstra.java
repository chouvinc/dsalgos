import java.util.*;

public class Dijkstra {
    private static int[] distance;
    private static boolean[] optimal;

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        distance = new int[adj.length];
        optimal = new boolean[adj.length];

        for (int i=0; i<adj.length; i++)
            distance[i] = Integer.MAX_VALUE;

        List<Integer> q = new ArrayList();
        q.add(s);
        distance[s] = 0;

        while (!q.isEmpty()) {
            int min = findMin(q);

            if (optimal[t]) {
                break;
            }
            // if there are neighbors to min
            if (adj[min].size() > 0) {
                for (int i=0; i<adj[min].size(); i++) {
                    int newDist = distance[min] + cost[min].get(i);
                    int neighbor = adj[min].get(i);

                    if (distance[neighbor] > newDist) {
                        distance[neighbor] = newDist;
                    }

                    if (!optimal[neighbor]) {
                        q.add(neighbor);
                    }
                }
            }
        }


        if (distance[t] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return distance[t];
        }
    }

    public static int findMin(List<Integer> list) {
        int min = list.get(0);
        int index = 0;

        for (int i=1; i<list.size(); i++) {
            int currNode = list.get(i);
            if (!optimal[currNode] && distance[min] > distance[currNode]) {
                min = currNode;
                index = i;
            }
        }

        list.remove(index);
        optimal[min] = true;
        return min;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

