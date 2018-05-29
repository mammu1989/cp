package uva;

import java.io.DataInputStream;
import java.io.IOException;

public class UVA10424 {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        String s1;
        String s2;

        while ((s1 = reader.readLine()) != null && !s1.isEmpty()) {
            s2 = reader.readLine();

            int n1 = f(s1.toLowerCase());
            int n2 = f(s2.toLowerCase());

            System.out.printf("%.2f %%\n", Math.min(n1, n2) / (float)Math.max(n1, n2) * 100f);
        }
    }

    private static int f(String s1) {
        int s = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) >= 'a' && s1.charAt(i) <= 'z') {
                s += s1.charAt(i) - 'a' + 1;
            }
        }

        return g(s);
    }

    private static int g(int s) {
        if(s < 10) return s;

        int sum = 0;

        while (s != 0) {
            sum += s % 10;
            s /= 10;
        }

        return g(sum);
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
