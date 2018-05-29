package codejam;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PS2018SE {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int t = reader.nextInt();

        for (int i = 0; i < t; i++) {
            int n = reader.nextInt();
            PriorityQueue<Node> pq = new PriorityQueue<>(n, (a,b) -> b.v - a.v);

            for (int j = 0; j < n; j++) {
                pq.add(new Node(reader.nextInt(), (char)(j+'A')));
            }

            System.out.printf("Case #%d:", i + 1);

            while (!pq.isEmpty()) {
                if(pq.size() == 2 && ((Node)pq.toArray()[0]).v == ((Node)pq.toArray()[1]).v) {
                    int v = ((Node)pq.toArray()[0]).v;
                    char c1 = ((Node)pq.toArray()[0]).p;
                    char c2 = ((Node)pq.toArray()[1]).p;

                    for (int j = 0; j < v; j++) {
                        System.out.print(" " + c1 + c2);
                    }
                    pq.clear();
                } else {
                    Node node = pq.poll();
                    node.v--;
                    System.out.print(" " + node.p);
                    if(node.v > 0) {
                        pq.offer(node);
                    }
                }
            }

            System.out.println();
        }
    }

    static class Node {
        int v;
        char p;

        public Node(int v, char p) {
            this.v = v;
            this.p = p;
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
