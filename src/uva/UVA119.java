package uva;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UVA119 {
    public static void main(String... args) throws IOException {
        InputReader inputReader = new InputReader();

        while (inputReader.hasNext()) {
            int n = inputReader.nextInt();

            List<String> f = new ArrayList<>(n);
            Map<String, Integer> m = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String s = inputReader.nextWord();
                f.add(s);
                m.put(s, 0);
            }

            for (int i = 0; i < n; i++) {
                String s = inputReader.nextWord();
                int money = inputReader.nextInt();
                int num = inputReader.nextInt();
                if(num != 0) {
                    m.put(s, m.get(s) + (money % num) - money);
                }

                for (int j = 0; j < num; j++) {
                    String fri = inputReader.nextWord();

                    m.put(fri, m.get(fri) + money / num);
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.println(f.get(i) + " " + m.get(f.get(i)));
            }

            if (inputReader.hasNext()) {
                System.out.println();
            }
        }
    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;
        InputReader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }
        boolean hasNext() throws IOException {
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

        int nextInt() throws IOException {
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

        String nextWord() throws IOException {
            String s;
            if ((tokenizer == null || !tokenizer.hasMoreTokens())) {
                s = reader.readLine();
                if(s == null || s.isEmpty()) {
                    throw new IOException("Input is empty");
                }
                tokenizer = new StringTokenizer(s);
            }
            return tokenizer.nextToken();
        }
    }
}
