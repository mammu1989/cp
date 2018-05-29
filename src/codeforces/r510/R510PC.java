package codeforces.r510;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class R510PC {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();
        List<Integer> zeros = new ArrayList<>();
        List<Integer> opsOne = new ArrayList<>();
        int n = reader.nextInt();
        int a[] = new int[n];
        int zeroCnt = 0;
        int nCnt = 0;
        int maxN = Integer.MIN_VALUE;
        int maxNInd = -1;

        for (int i = 0; i < n; i++) {
            a[i] = reader.nextInt();
            if(a[i] == 0) {
                zeroCnt++;
                zeros.add(i);
            } else if(a[i] < 0) {
                nCnt++;
                if(maxN < a[i]) {
                    if(maxN != Integer.MIN_VALUE) opsOne.add(maxNInd);
                    maxN = a[i];
                    maxNInd = i;
                } else {
                    opsOne.add(i);
                }
            } else {
                opsOne.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zeroCnt - 1; i++) {
            sb.append("1 ").append(zeros.get(i) + 1).append(" ").append(zeros.get(i + 1) + 1).append("\n");
        }

        if(zeroCnt >= 1 && nCnt % 2 == 0) {
            if(zeroCnt != n) sb.append("2 ").append(zeros.get(zeroCnt - 1) + 1).append("\n");
        } else if(zeroCnt >= 1 && nCnt % 2 == 1) {
            sb.append("1 ").append(maxNInd + 1).append(" ").append(zeros.get(zeroCnt - 1) + 1).append("\n");
            if(!(nCnt == 1 && zeroCnt + nCnt == n)) {
                sb.append("2 ").append(zeros.get(zeroCnt - 1) + 1).append("\n");
            }
        } else if(zeroCnt == 0 && nCnt % 2 == 1) {
            sb.append("2 ").append(maxNInd + 1).append("\n");
        }

        if(nCnt % 2 == 0 && maxNInd != -1) opsOne.add(maxNInd);

        for (int i = 0; i < opsOne.size() - 1; i++) {
            sb.append(1).append(" ").append(opsOne.get(i) + 1).append(" ").append(opsOne.get(i + 1) + 1).append("\n");
        }

        System.out.println(sb.toString());
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
