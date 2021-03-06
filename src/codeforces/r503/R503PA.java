package codeforces.r503;

import java.io.DataInputStream;
import java.io.IOException;

public class R503PA {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();
        int h = reader.nextInt();

        int a = reader.nextInt();
        int b = reader.nextInt();
        int k = reader.nextInt();

        for (int i = 0; i < k; i++) {
            int ta = reader.nextInt();
            int fa = reader.nextInt();
            int tb = reader.nextInt();
            int fb = reader.nextInt();
            int ans;
            if(ta == tb) {
                ans = Math.abs(fa - fb);
            } else if(fa <= a && fb <= a) {
                ans = (a - fa) + (a - fb) + Math.abs(ta - tb);
            } else if(fa >= b && fb >= b) {
                ans = (fb - b) + (fa - b) + Math.abs(ta - tb);
            } else {
                ans = Math.abs(fa - fb) + Math.abs(ta - tb);
            }

            System.out.println(ans);
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
