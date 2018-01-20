/**
 * Created by chouvinc on 11/18/2017.
 */
import java.util.*;
import java.io.*;

public class is_bst_hard {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        List<Integer> results = new ArrayList<Integer>();

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        void calculateResults(int i) {
            // Implement correct algorithm here
            if (tree.length == 0) {
                return;
            }
            Node currNode = tree[i];

            if (currNode.left != -1) {
                if (currNode.key == tree[currNode.left].key) {
                    results.add(-1);
                }
                calculateResults(currNode.left);
            }

            results.add(currNode.key);

            if (currNode.right != -1) {
                calculateResults(currNode.right);
            }
        }

        boolean isBinarySearchTree() {
            if (tree.length == 0) {
                return true;
            }

            for (int j=0; j<results.size()-1; j++) {
                if (results.get(j+1) < results.get(j) || results.get(j) == -1 || results.get(j+1) == -1) {
                    return false;
                }
            }

            return true;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        tree.calculateResults(0);
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
