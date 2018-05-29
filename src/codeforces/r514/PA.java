package codeforces.r514;

import java.io.DataInputStream;
import java.io.IOException;

public class PA {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        int l = reader.nextInt();
        int a = reader.nextInt();
        int t[] = new int[n];
        int li[] = new int[n];
        int s = 0;

        if(n == 0) {
            System.out.println(l / a);
            return;
        }

        for (int i = 0; i < n; i++) {
            t[i] = reader.nextInt();
            li[i] = reader.nextInt();
        }

        if(t[0] >= a) s += t[0] / a;
        System.out.println(s);
        if(l - t[n - 1] - li[n - 1] >= a) s += (l - t[n - 1] - li[n - 1]) / a;
        System.out.println(s);
        for (int i = 0; i < n - 1; i++) {
            if(t[i + 1] - t[i] - li[i] >= a) {
                s += (t[i + 1] - t[i] - li[i]) / a;
            }
        }

        System.out.println(s);
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
