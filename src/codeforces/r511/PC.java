package codeforces.r511;

import org.omg.CORBA.MARSHAL;

import java.io.DataInputStream;
import java.io.IOException;

public class PC {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();

        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = reader.nextInt();
        }

        int gcd = a[0];
        for (int i = 1; i < n; i++) {
            gcd = gcd(gcd, a[i]);
        }
        int b[] = new int[20000000];
        int max = 0;
        for (int i = 0; i < n; i++) {
            a[i] = a[i] / gcd;
            b[a[i]]++;
            max = Math.max(max, a[i]);
        }
        if(b[1] == n) {
            System.out.println(-1);
            return;
        }
        boolean sieve[] = new boolean[max + 1];
        int ans = n - 1;
        for (int i = 2; i <= max; i++) {
            int j = i;
            int cnt = 0;
            if(!sieve[j]) {
                while (j <= max) {
                    sieve[j] = true;
                    cnt += b[j];
                    j = j + i;
                }
            }
            ans = Math.min(ans, (n - cnt));
        }

        System.out.println(ans);
    }

    static private int gcd(int p, int q) {
        if (p % q == 0)
            return q;
        return gcd(q, p % q);
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
