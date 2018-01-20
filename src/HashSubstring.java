import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    private static long prime = 500009;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static int hashFunc(String s, int multiplier) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int) hash;
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        int multiplier = 1;
        String s = input.pattern, t = input.text;
        int patternHash = hashFunc(s, multiplier);
        int[] hash = preHash(t, s.length(), multiplier);
        List<Integer> occurrences = new ArrayList<>();
        for (int i=0; i <= t.length()-s.length(); i++) {
            if (patternHash != hash[i]) {
                continue;
            }
            boolean passed = true;
            for (int j=i; j<i+s.length(); j++) {
                if (t.charAt(j) != s.charAt(j-i)) {
                    passed = false;
                    break;
                }
            }
            if (passed) occurrences.add(i);
        }

        return occurrences;
    }

    private static int[] preHash(String text, int patternLength, int multiplier) {
        int[] myHashes = new int[text.length()-patternLength+1];
        String sub = text.substring(text.length() - patternLength, text.length());

        myHashes[text.length()-patternLength] = hashFunc(sub, multiplier);

        int y = 1;
        for (int i=1; i<= patternLength; i++) {
            y = (int)((y*multiplier) % prime);
        }

        for (int i=text.length() - patternLength - 1; i>=0; i--) {
            myHashes[i] =  (int) ((multiplier*myHashes[i+1] + text.charAt(i) - y*text.charAt(i + patternLength))%prime);
        }

        return myHashes;
    }
//
//    private static boolean areEqual(String substring, String pattern) {
//        return substring.length() == pattern.length() && substring.equals(pattern);
//    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

