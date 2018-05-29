package codejam;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class QR2018TS {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int t = reader.nextInt();
        for (int i = 0; i < t; i++) {
            int n = reader.nextInt();

            int a[] = new int[n];
            int a1[] = new int[(n / 2) + (n % 2)];
            int a2[] = new int[n / 2];
            for (int j = 0; j < n / 2; j++) {
                a1[j] = reader.nextInt();
                a2[j] = reader.nextInt();
            }
            if(n % 2 == 1) {
                a1[a1.length - 1] = reader.nextInt();
            }

            Arrays.sort(a1);
            Arrays.sort(a2);
            for (int j = 0; j < a1.length; j++) {
                a[j * 2] = a1[j];
            }
            for (int j = 0; j < a2.length; j++) {
                a[j * 2 + 1] = a2[j];
            }
            int wrongIndex = -1;
            for (int j = 0; j < a.length - 1; j++) {
                if(a[j] > a[j + 1]) {
                    wrongIndex = j;
                    break;
                }
            }
            if(wrongIndex == -1) {
                System.out.printf("Case #%d: OK\n", i + 1);
            } else {
                System.out.printf("Case #%d: %d\n", i + 1, wrongIndex);
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
