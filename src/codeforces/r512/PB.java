package codeforces.r512;

import java.io.DataInputStream;
import java.io.IOException;

public class PB {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();
        int d = reader.nextInt();
        int m = reader.nextInt();

        for (int i = 0; i < m; i++) {
            int x = reader.nextInt();
            int y = reader.nextInt();
            int diff = y - x;
            int sum = y + x;
            if(diff >= -d && diff <= d && sum >= d && sum <= 2 * n - d) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
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
