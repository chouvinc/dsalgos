import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {
            List[] nodes = new List[n];
            int root = -1;
            int counter = 1;

            for (int i=0; i<n; i++) {
                nodes[i] = new ArrayList<Integer>();
            }

            for (int i=0; i<n; i++) {
                if (parent[i] == -1) {
                    root = i;
                } else {
                    nodes[parent[i]].add(i);
                }
            }

            List<List> q = new LinkedList<List>();
            q.add(nodes[root]);

            while (!q.isEmpty()) {
                // dequeue current node
                List<Integer> currentLevel = q.get(0);
                q.remove(0);

                if (currentLevel.size() > 0) {
                    List<Integer> listLevel = new ArrayList<>();

                    for (int value: currentLevel) {
                        if (nodes[value].size() > 0) {
                            for (int i=0; i<nodes[value].size(); i++) {
                                listLevel.add((Integer) nodes[value].get(i));
                            }
                        }
                    }

                    q.add(listLevel);
                    counter++;
                }

            }

            return counter;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
