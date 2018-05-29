package codeforces.r514;

import java.io.DataInputStream;
import java.io.IOException;

public class PB {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();
        String nm = reader.readLine();
        int n = Integer.parseInt(nm.split(" ")[0].trim());
        int m = Integer.parseInt(nm.split(" ")[1].trim());

        char s[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            String t = reader.readLine().trim();

            s[i] = t.toCharArray();
        }
        
        char g[][] = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = '.';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i + 2 < n && j + 2 < m && toFill(s, i, j)) {
                    fill(g, i, j);
                }
                if(g[i][j] != s[i][j]) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
        return;
    }

    private static void fill(char[][] g, int i, int j) {
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if(k == 1 && l == 1) continue;
                int x = i + k;
                int y = j + l;
                g[x][y] = '#';
            }
        }
    }

    private static boolean toFill(char[][] s, int i, int j) {
        if(s[i][j] == '.') return false;
        
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if(k == 1 && l == 1) continue;
                int x = i + k;
                int y = j + l;
                if (s[x][y] != '#') return false;
            }
        }

        return true;
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
