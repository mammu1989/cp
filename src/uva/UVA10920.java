package uva;

import java.io.DataInputStream;
import java.io.IOException;

public class UVA10920 {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n;
        long p;

        while ((n = reader.nextInt()) != 0 && (p = reader.newLong()) != 0) {
            if(p == 1) {
                int ring = (n - 1) / 2;
                System.out.printf("Line = %d, column = %d.\n", ring + 1, ring + 1);
                continue;
            }

            long ringNumber = getRingNumber(p);
            long prevRing = ringNumber - 1;
            long ringPos = p - ((2 * prevRing + 1) * (2 * prevRing + 1)) - 1;
            long segLen = ringNumber * 2;
            int outer = (n - 1) / 2;
            int row;
            int col;
            if(ringPos / segLen == 0) {
                row = outer - (int)ringNumber;
                col = outer + (int)prevRing - (int)ringPos;
            } else if(ringPos / segLen == 1) {
                ringPos = ringPos % segLen;
                row = outer - (int)prevRing + (int)ringPos;
                col = outer - (int)ringNumber;
            } else if(ringPos / segLen == 2) {
                ringPos = ringPos % segLen;
                row = outer + (int)ringNumber;
                col = (int)(outer - prevRing) + (int)ringPos;
            } else {
                ringPos = ringPos % segLen;
                row = outer + (int)prevRing - (int)ringPos;
                col = outer + (int)ringNumber;
            }

            System.out.printf("Line = %d, column = %d.\n", n - row, col + 1);
        }
    }

    private static long getRingNumber(long p) {
        long ring = 1;

        while (ring * ring < p) {
            ring = ring + 2;
        }

        return (ring - 1) / 2;
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

        public long newLong() throws IOException {
            long ret = 0;
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
