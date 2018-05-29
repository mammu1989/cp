package codeforces.practice.ds;

import java.io.DataInputStream;
import java.io.IOException;

public class R484PD {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();
        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = reader.nextInt();
        }

        DisJointSet dsu = new DisJointSet(n);


    }

    static class DisJointSet {
        int[] table, rank, count;
        int size;

        DisJointSet(int size) {
            this.table = new int[size];
            this.rank = new int[size];
            this.count = new int[size];
            this.size = size;
            for (int i = 0; i < size; i++) {
                this.table[i] = i;
                this.rank[i] = 1;
                this.count[i] = 1;
            }
        }

        boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        int find(int node) {
            if (table[node] == node) {
                return node;
            } else {
                return table[node] = find(table[node]);
            }
        }

        void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y)
                this.size--;
            if (rank[x] < rank[y]) {
                table[x] = y;
                count[y] += count[x];
            } else if (rank[x] > rank[y]) {
                table[y] = x;
                count[x] += count[y];
            } else if (x != y) {
                table[y] = x;
                count[x] += count[y];
                rank[x]++;
            }
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
