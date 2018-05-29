package uva;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVA11559 {
    public static void main(String... args) throws IOException {
        InputReader reader = new InputReader();
        while(reader.hasNext()) {
            int ans = Integer.MAX_VALUE;
            int n = reader.nextInt();
            int b = reader.nextInt();
            int h = reader.nextInt();
            int w = reader.nextInt();

            for (int i = 0; i < h; i++) {
                int p = reader.nextInt();
                for (int j = 0; j < w; j++) {
                    int a = reader.nextInt();
                    if(n <= a && (n * p) <= b) {
                        ans = Math.min(ans, n * p);
                    }
                }
            }

            if(ans != Integer.MAX_VALUE) {
                System.out.println(ans);
            } else {
                System.out.println("stay home");
            }
        }
    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;
        public InputReader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }
        public boolean hasNext() throws IOException {
            String s;
            if ((tokenizer == null || !tokenizer.hasMoreTokens())) {
                s = reader.readLine();
                if(s == null || s.isEmpty()) {
                    return false;
                }
                tokenizer = new StringTokenizer(s);
            }

            return tokenizer.hasMoreTokens();
        }
        public int nextInt() throws IOException {
            String s;
            if ((tokenizer == null || !tokenizer.hasMoreTokens())) {
                s = reader.readLine();
                if(s == null || s.isEmpty()) {
                    throw new IOException("Input is empty");
                }
                tokenizer = new StringTokenizer(s);
            }
            return Integer.parseInt(tokenizer.nextToken());
        }
    }
}
