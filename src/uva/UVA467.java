package uva;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UVA467 {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        String s;
        int test = 1;
        while ((s = reader.readLine()) != null && !s.isEmpty()) {
            StringTokenizer st = new StringTokenizer(s);
            List<Integer> l = new ArrayList<>();
            int min = Integer.MAX_VALUE;
            while (st.hasMoreTokens()) {
                int time = Integer.parseInt(st.nextToken());
                l.add(time);
                min = Math.min(min, time);
            }

            boolean isSynced = true;
            for (int i = 2 * min; i <= 3600; i++) {
                isSynced = true;
                for (int j = 0; j < l.size(); j++) {
                    int x = i % (l.get(j) * 2);

                    if(x >= l.get(j) - 5) {
                        isSynced = false;
                        break;
                    }
                }

                if(isSynced) {
                    int minute = i / 60;
                    int second = i % 60;
                    System.out.println(String.format("Set %d synchs again at %d minute(s) and %d second(s) " +
                            "after all turning green.", test, minute, second));
                    break;
                }
            }

            if(!isSynced) {
                System.out.println(String.format("Set %d is unable to synch after one hour.", test));
            }

            test++;
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

//    PF50 PF25 pepperfry offers.