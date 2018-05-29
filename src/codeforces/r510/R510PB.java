package codeforces.r510;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class R510PB {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();

        HashMap<String, Integer> juices = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int cost = reader.nextInt();
            char j[] = reader.nextWord().toCharArray();
            Arrays.sort(j);
            String juice = new String(j);
            if(juices.containsKey(juice)) {
                juices.put(juice, Math.min(cost, juices.get(juice)));
            } else {
                juices.put(juice, cost);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> e1 : juices.entrySet()) {
            if(isCorrect(e1.getKey())) {
                ans = Math.min(ans, e1.getValue());
                continue;
            }
            for (Map.Entry<String, Integer> e2 : juices.entrySet()) {
                if(isCorrect(e1.getKey() + e2.getKey())) {
                    ans = Math.min(ans, e1.getValue() + e2.getValue());
                    continue;
                }
                for (Map.Entry<String, Integer> e3 : juices.entrySet()) {
                    if(isCorrect(e1.getKey() + e2.getKey() + e3.getKey())) {
                        ans = Math.min(ans, e1.getValue() + e2.getValue() + e3.getValue());
                    }
                }
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static boolean isCorrect(String s) {
        return s.contains("A") && s.contains("B") && s.contains("C");
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
