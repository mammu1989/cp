package codeforces.practice.ds;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Educational47 {
    private static int dominant[];
    private static int depth[];
    private static int g[][];

    public static void main(String...args) throws InterruptedException {
        Educational47 main = new Educational47();
        Thread t = new Thread(null, null, "~", Runtime.getRuntime().maxMemory()){
            @Override
            public void run() {
                try {
//                    long t1 = System.currentTimeMillis();
                    main.asdf();
//                    System.out.println(System.currentTimeMillis() - t1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        t.join();
    }

    static int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    public void asdf() throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();

        dominant = new int[n+1];
        depth = new int[n+1];
        g = getTree(reader, n);
        dfs(1, 0, 0);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(dominant[i + 1] - depth[i + 1]).append("\n");
        }
        System.out.print(sb.toString());
    }

    private int[][] getTree(Reader reader, int n) throws IOException {
        int from[] = new int[n-1];
        int to[] = new int[n-1];

        for (int i = 0; i < n - 1; i++) {
            from[i] = reader.nextInt();
            to[i] = reader.nextInt();
        }

        return packU(n + 1, from, to);
    }

    private Map<Integer, Integer> dfs(int node, int depths, int parent) {
        depth[node] = depths;
        int maxV = 1;
        dominant[node] = depths;
        Map<Integer, Integer> m1 = null;
        for (int child : g[node]) {
            if(child != parent) {
                Map<Integer, Integer> m2 = dfs(child, depths + 1, node);
                if(m1 == null) {
                    m1 = new TreeMap<Integer, Integer>() {{ put(depths, 1); }};
                }
                if(m1.size() < m2.size()) {
                    dominant[node] = dominant[child];
                    maxV = m2.get(dominant[child]);
                    Map<Integer, Integer> tmp = m1;
                    m1 = m2;
                    m2 = tmp;
                }

                for (Map.Entry<Integer, Integer> entry : m2.entrySet()) {
                    int val = m1.getOrDefault(entry.getKey(), 0) + entry.getValue();
                    m1.put(entry.getKey(), val);
                    if(val > maxV || (maxV == val && entry.getKey() < dominant[node])) {
                        maxV = val;
                        dominant[node] = entry.getKey();
                    }
                }
            }
        }

        return (m1 == null) ? new TreeMap<Integer, Integer>() {{ put(depths, 1); }} : m1;
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 15;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        //Will read words with space as delimiters
        public String nextWord() throws IOException {
            byte[] buf = new byte[64]; // word length

            byte c = read();
            while (c == ' ')
                c = read();
            int cnt = 0;
            do {
                buf[cnt++] = c;
                c = read();
            } while (c != ' ' && c != '\n');

            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
