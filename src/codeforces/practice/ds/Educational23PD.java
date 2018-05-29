package codeforces.practice.ds;

import java.io.DataInputStream;
import java.io.IOException;

public class Educational23PD {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = reader.nextInt();
        }

        long s1 = solve(a);

        for (int i = 0; i < n; i++) {
            a[i] = -a[i];
        }

        System.out.println(s1 + solve(a));
    }

    private static long solve(int[] a) {
        int l[] = new int[a.length];
        int r[] = new int[a.length];
        long sum = 0;

        for (int i = 0; i < a.length; i++) {
            l[i] = i - 1;
            while (l[i] >= 0 && a[i] > a[l[i]]) {
                l[i] = l[l[i]];
            }
        }

        for (int i = a.length - 1; i >= 0; i--) {
            r[i] = i + 1;
            while (r[i] < a.length && a[i] >= a[r[i]]) {
                r[i] = r[r[i]];
            }

            sum += (long)(i - l[i]) * (long)(r[i] - i) * (long)a[i];
        }

        return sum;
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
