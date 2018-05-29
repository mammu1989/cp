package codeforces.practice.ds;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class R234PD {
    public static void main(String... args) throws IOException {
        Reader reader = new Reader();

        int n = reader.nextInt();
        int m = reader.nextInt();
        int k = reader.nextInt();
        List<Integer> adjList[] = new List[n + 1];
        int groupNumber[] = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            adjList[i] = new LinkedList<>();
        }

        int c[] = new int[k];
        int d[][] = new int[k][k];

        int index = 1;
        for (int i = 0, offset = 0; i < k; i++, index += offset, offset = 0) {
            c[i] = reader.nextInt();
            while (offset < c[i]) {
                groupNumber[index + offset] = i;
                offset++;
            }
        }

        for (int i = 0; i < k; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();
            int x = reader.nextInt();

            if(x == 0){
                adjList[u].add(v);
                adjList[v].add(u);
            }
            if(groupNumber[u] != groupNumber[v]) {
                d[groupNumber[v]][groupNumber[u]] = d[groupNumber[u]][groupNumber[v]] =
                        Math.min(x, d[groupNumber[u]][groupNumber[v]]);
            }
        }

        boolean isVisited[] = new boolean[n + 1];
        boolean isCorrect = true;
        boolean isGroupVisited[] = new boolean[k];

        for (int i = 1; i <= n; i++) {
            if(!isVisited[i]) {
                TreeSet<Integer> groups = new TreeSet<>();
                dfs(i, isVisited, adjList, groups, groupNumber);
                for (int group : groups) {
                    if(isGroupVisited[group]) {
                        isCorrect = false;
                    } else {
                        isGroupVisited[group] = true;
                    }
                }

                if(!isCorrect) {
                    break;
                }
            }
        }

        if(isCorrect) {
            System.out.println("Yes");
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    for (int l = 0; l < k; l++) {
                        if(d[j][i] == Integer.MAX_VALUE || d[i][l] == Integer.MAX_VALUE) {
                            continue;
                        }
                        d[j][l] = Math.min(d[j][l] , d[j][i] + d[i][l]);
                    }
                }
            }

            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if(i == j) {
                        d[i][j] = 0;
                        continue;
                    }
                    if(d[i][j] == Integer.MAX_VALUE) {
                        d[i][j] = -1;
                    }
                }
            }

            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    System.out.print(d[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No");
        }
    }

    private static int dfs(int node, boolean[] isVisited, List<Integer>[] adjList, TreeSet<Integer> groups, int[] groupNumber) {
        int cnt = 1;

        isVisited[node] = true;
        groups.add(groupNumber[node]);
        for (int child : adjList[node]) {
            if (!isVisited[child]) {
                cnt += dfs(child, isVisited, adjList, groups, groupNumber);
            }
        }

        return cnt;
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