import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    private List<List<String>> table = new ArrayList<>();

    // for hash function
    private int bucketCount;
    private long prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
//         out.flush();
    }

    private boolean findInChain(List<String> list, String queryString) {
        for (String item: list) {
            if (item.equals(queryString)) {
                return true;
            }
        }
        return false;
    }

    private void removeFromChain(List<String> list, String queryString) {
        String toRemove = "";
        for (String item: list) {
            if (item.equals(queryString)) {
                toRemove = item;
            }
        }

        list.remove(toRemove);
    }

    private void processQuery(Query query) {
        int hash = 0;
        if (query.s != null){
            hash = hashFunc(query.s);
        } else {
            hash = query.ind;
        }
        switch (query.type) {
            case "add":
                if (!findInChain(table.get(hash), query.s)) {
                    List<String> oldList = table.get(hash);
                    oldList.add(0, query.s);
                    table.set(hash, oldList);
                }
                break;
            case "del":
                removeFromChain(table.get(hash), query.s);
                break;
            case "find":
                writeSearchResult(findInChain(table.get(hash), query.s));
                break;
            case "check":
                for (String item: table.get(hash)) {
                    if (!item.equals("")) {
                        out.print(item + " ");
                    }
                }
                out.println();
                // Uncomment the following if you want to play with the program interactively.
//                out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        int queryCount = in.nextInt();
        for (int i=0; i<bucketCount; i++) {
            List<String> newList = new ArrayList<>();
            table.add(newList);
        }
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
