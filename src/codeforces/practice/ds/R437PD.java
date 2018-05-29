package codeforces.practice.ds;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class R437PD {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();
        int m = reader.nextInt();

        Node a[] = new Node[n];
        ArrayList<LinkedList<Integer>> adjList = new ArrayList<>(n);
        for (int i = 0; i < m; i++) {
            adjList.add(new LinkedList<>());
        }

        for (int i = 0; i < n; i++) {
            a[i] = new Node(i, reader.nextInt());
        }

        for (int i = 0; i < m; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();

            adjList.get(u - 1).add(v - 1);
            adjList.get(v - 1).add(u - 1);
        }

        DSU dsu = new DSU(n);

        Arrays.sort(a, (x,y) -> y.v - x.v);

        for (int i = 0; i < n; i++) {
            for(int child : adjList.get(a[i].n)) {
                dsu.union(a[i].n, child);
            }
        }
    }

    static class Node {
        int n;
        int v;

        public Node(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }

    static class DSU
    {
        int[] sz, p;

        DSU(int n)
        {
            p = new int[n];
            sz = new int[n];
            for(int i = 0; i < n; ++i)
            {
                p[i] = i;
                sz[i] = 1;
            }
        }

        int find(int x) { return x == p[x] ? x : (p[x] = find(p[x])); }

        long union(int x, int y)
        {
            x = find(x);
            y = find(y);
            if(x == y)
                return 0;
            long ret = 1l * sz[x] * sz[y];
            if(sz[x] > sz[y])
            {
                p[y] = x;
                sz[x] += sz[y];
            }
            else
            {
                p[x] = y;
                sz[y] += sz[x];
            }
            return ret;
        }

    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
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
