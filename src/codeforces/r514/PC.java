package codeforces.r514;

import java.io.DataInputStream;
import java.io.IOException;

public class PC {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        StringBuilder sb = new StringBuilder();
        print(n, 1, n, sb);
        System.out.println(sb.toString());
    }

    private static void print(int n, int pow, int max, StringBuilder sb) {
        if(n == 1) sb.append(pow);
        else if(n == 2) sb.append(pow + " " + (pow * 2));
        else if(n == 3) sb.append(pow + " " + pow + " " + max);
        else {
            for (int i = 0; i < (n / 2) + (n % 2); i++) sb.append(pow + " ");
            if (n % 2 == 1) max -= pow;
            print(n / 2, pow * 2, max, sb);
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
