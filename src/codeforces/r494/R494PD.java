package codeforces.r494;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

public class R494PD {
    public static void main(String[] args) throws IOException {
        Reader scanner = new Reader();
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int a[] = new int[31];
        for (int i = 0; i < n; i++) {
            a[log2(scanner.nextInt())]++;
        }
        for (int i = 0; i < q; i++) {
            int c = scanner.nextInt();
//            int cnt = 0;
            int ans = 0;
            for (int j = 30; j >= 0; j--) {
//                if((c & (1<<j)) != 0) {
//                    cnt++;
//                }
//                if(cnt > 0) {
//                    if(a[j] > 0) {
//                        ans = ans + Math.min(cnt, a[j]);
//                        cnt = cnt > a[j] ? cnt - a[j] : 0;
//                    } else {
//                        cnt *= 2;
//                    }
//                }
                if((1 << j) <= c && a[j] > 0) {
                    int present = Math.min(c / (1 << j), a[j]);
                    ans += present;
                    c -= present * (1 << j);
                }
            }
            if(c == 0) {
                System.out.println(ans);
            } else {
                System.out.println(-1);
            }
        }
    }

    private static int log2(int i) {
        int k = 0;
        while (i != 1) {
            i = i / 2;
            k++;
        }
        return k;
    }


    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
