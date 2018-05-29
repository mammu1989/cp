package codeforces.practice.ds;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class R120PE {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();
        int m = reader.nextInt();

        Set<Integer>[] g = new Set[n];
        for (int i = 0; i < n; i++) {
            g[i] = new TreeSet<>();
        }

        int u, v;
        for (int i = 0; i < m; i++) {
            u = reader.nextInt();
            v = reader.nextInt();

            g[u - 1].add(v - 1);
            g[v - 1].add(u - 1);
        }

        BitSet nodes = new BitSet(n);
        nodes.flip(0, n);

        Queue<Integer> queue = new LinkedList<>();
        int k = 0;
        LinkedList<Integer> sizes = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(nodes.get(i)) {
                int size = 0;
                queue.offer(i);
                nodes.flip(i);
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    size++;
                    for (int j = nodes.nextSetBit(0); j != -1; j = nodes.nextSetBit(j + 1)) {
                        if(!g[node].contains(j)) {
                            nodes.flip(j);
                            queue.offer(j);
                        }
                    }
                }
                k++;
                sizes.add(size);
            }
        }
        StringBuilder ans = new StringBuilder();
        sizes.sort(Integer::compareTo);
        ans.append(k).append("\n");
        for (int size : sizes) {
            ans.append(size).append(" ");
        }
        System.out.println(ans.toString());
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
