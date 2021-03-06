package uva;

import java.io.DataInputStream;
import java.io.IOException;

public class UVA1062 {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        String s;
        int ind = 1;
        while (!(s = reader.readLine().trim()).equals("end")) {
            int st[] = new int[26];
            int stCnt = 0;

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                boolean isStacked = false;
                for (int j = 0; j < stCnt; j++) {
                    if(ch <= st[j]) {
                        st[j] = ch;
                        isStacked = true;
                        break;
                    }
                }

                if(!isStacked) {
                    st[stCnt] = ch;
                    stCnt++;
                }
            }

            System.out.println(String.format("Case %d: %d", ind, stCnt));
            ind++;
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
            byte[] buf = new byte[1010]; // line length
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
