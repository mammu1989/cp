package codeforces.r509;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;

public class PD {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();
        int h = reader.nextInt();

        int y[] = new int[n + 1];
        int x1[] = new int[n + 1];
        int x2[] = new int[n + 1];
        y[0] = h;

        TreeMap<Integer, Node> ts = new TreeMap<>(Comparator.naturalOrder());
        for (int i = 1; i <= n; i++) {
            x1[i] = reader.nextInt();
            x2[i] = reader.nextInt();

            y[i] = y[i - 1] - (x1[i] - x2[i - 1]);
            ts.put(y[i], new Node(y[i], x1[i], x2[i]));
        }

//        System.out.println(ts);
//
//        for (int i = 0; i <= n; i++) {
//            System.out.println(y[i] + " " + x1[i] + " " + x2[i]);
//        }
        
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int yDist = ts.higherKey(y[i] - h);
            if(ts.containsKey(y[i] - h)) {
                yDist = y[i] - h;
                ans = Math.max(ans, ts.get(yDist).x1 - x1[i]);
            } else {
                ans = Math.max(ans, ts.get(yDist).x2 - x1[i] + yDist - y[i] + h);
            }
//            System.out.println(y[i] + " " + yDist);
        }

        System.out.println(ans);
    }

    static class Node {
        int y;
        int x1;
        int x2;

        public Node(int y, int x1, int x2) {
            this.y = y;
            this.x1 = x1;
            this.x2 = x2;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x1=" + x1 +
                    ", x2=" + x2 +
                    '}';
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
