package codejam;

import java.io.DataInputStream;
import java.io.IOException;

public class R1A2018WC {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();
        int t = reader.nextInt();

        for (int z = 0; z < t; z++) {
            int r = reader.nextInt();
            int c = reader.nextInt();
            int h = reader.nextInt();
            int v = reader.nextInt();

            char w[][] = new char[r][];
            int rc[] = new int[r + 1];
            int cc[] = new int[c + 1];
            int sum = 0;
            for (int i = 0; i < r; i++) {
                w[i] = reader.nextWord().toCharArray();
                for (int j = 0; j < c; j++) {
                    if (w[i][j] == '@') {
                        sum++;
                        rc[i + 1]++;
                        cc[j + 1]++;
                    }
                }
            }

            for (int i = 1; i <= r; i++) {
                rc[i] = rc[i] + rc[i - 1];
            }

            for (int i = 1; i <= c; i++) {
                cc[i] = cc[i] + cc[i - 1];
            }

            if (sum % ((h + 1) * (v + 1)) != 0) {
                System.out.println("IMPOSSIBLE");
                break;
            }

            int rCap = sum / (h + 1);
            int hi[] = new int[h + 2];
            int vCap = sum / (c + 1);
            int ci[] = new int[c + 2];
//            if(!createIntervals(rCap, hi, rc)) {
//                System.out.println("IMPOSSIBLE");
//            }
//            if(!createIntervals(vCap, ci, cc)) {
//                System.out.println("IMPOSSIBLE");
//            }

            for (int i = 1; i < h + 2; i++) {
                for (int j = 1; j < c + 2; j++) {
//                    for (int k = hi[i - 1]; k < ; k++) {
//
//                    }
                }
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
