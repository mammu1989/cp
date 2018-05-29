package codeforces.practice.ds;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

public class R485PC {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();
        int m = reader.nextInt();

        int a[] = new int[m];
        boolean set[] = new boolean[(1 << n)];
        for (int i = 0; i < m; i++) {
            a[i] = reader.nextInt();
            set[a[i]] = true;
        }
        int count = 0;

        boolean isV1[] = new boolean[1 << n];
        boolean isV2[] = new boolean[1 << n];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            if(isV1[a[i]]) {
                continue;
            }
            count++;
            queue.offer(a[i]);
            isV1[a[i]] = true;
            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int j = 0; j < n; j++) {
                    if((node & (1 << j)) == 0) {
                        int child = (node | (1 << j));
                        if(!isV2[child]) {
                            queue.offer(child);
                            isV2[child] = true;
                        }
                    }
                }

                int child = (1 << n) - node - 1;
                if(!isV1[child] && set[child]) {
                    isV1[child] = true;
                    queue.offer(child);
                }
            }
        }

        System.out.println(count);
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
