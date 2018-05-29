package codejam;

import java.io.DataInputStream;
import java.io.IOException;

public class QR2018STUA {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int t = reader.nextInt();
        for (int i = 0; i < t; i++) {
            int d = reader.nextInt();
            char s[] = reader.nextWord().toCharArray();

            boolean isSwapped = true;
            int swaps = 0;
            if(d >= count(s)) {
                System.out.printf("Case #%d: %d\n", i + 1, 0);
                continue;
            }
            while (isSwapped) {
                isSwapped = false;
                int j = s.length - 1;
                while (j >= 0 && s[j] == 'C') {
                    j--;
                }

                while (j >= 0) {
                    if (s[j] == 'C') {
                        s[j] = 'S';
                        s[j + 1] = 'C';
                        isSwapped = true;
                        swaps++;
                        break;
                    }
                    j--;
                }

                if(!isSwapped) {
                    System.out.printf("Case #%d: IMPOSSIBLE\n", i + 1);
                } else if(d >= count(s)) {
                    System.out.printf("Case #%d: %d\n", i + 1, swaps);
                    break;
                }
            }
        }
    }

    private static long count(char[] s) {
        long sum = 0;
        long damage = 1;

        for (int i = 0; i < s.length; i++) {
            if(s[i] == 'C') {
                damage *= 2;
            } else {
                sum += damage;
            }
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
