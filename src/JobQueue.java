import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        PriorityQueue<Thread> q = new PriorityQueue <Thread> (numWorkers, (a, b) -> {
            long val1 = a.availableAt;
            long val2 = b.availableAt;

            int difference = (int) (val1 - val2);

            if (difference == 0) {
                return a.workerId - b.workerId;
            } else {
                return difference;
            }
        });

        for (int i = 0; i < numWorkers; i++) {
            q.add(new Thread(i, 0));
        }

        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker;
            Thread bestThread;

            bestThread = q.poll();
            bestWorker = bestThread.workerId;

            assignedWorker[i] = bestWorker;
            startTime[i] = bestThread.availableAt;

            bestThread.availableAt += duration;
            q.add(bestThread);
        }
    }

    static class Thread {
        int workerId;
        long availableAt;

        public Thread(int workerId, long availableAt) {
            this.workerId = workerId;
            this.availableAt = availableAt;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
