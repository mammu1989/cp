package codeforces.practice.ds;

import java.util.Scanner;

public class R464PD {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String w1 = scanner.next();
        String w2 = scanner.next();

        boolean adJMat[][] = new boolean[26][26];
        StringBuilder tree = new StringBuilder();

        for (int i = 0; i < n; i++) {
            adJMat[w1.charAt(i) - 'a'][w2.charAt(i) - 'a'] = true;
            adJMat[w2.charAt(i) - 'a'][w1.charAt(i) - 'a'] = true;
        }

        boolean isVisited[] = new boolean[26];
        int numEdges = 0;
        for (int i = 0; i < 26; i++) {
            if(!isVisited[i]) {
                int numNodes = dfs(i, isVisited, adJMat, tree);
                numEdges += numNodes - 1;
            }
        }

        System.out.println(numEdges + "\n" + tree.toString());
    }

    private static int dfs(int node, boolean[] isVisited, boolean[][] adJMat, StringBuilder tree) {
        isVisited[node] = true;

        int numNodes = 1;

        for (int child = 0; child < 26; child++) {
            if(adJMat[node][child] && !isVisited[child]) {
                tree.append((char)(node + 'a')).append(" ").append((char)(child + 'a')).append("\n");
                numNodes += dfs(child, isVisited, adJMat, tree);
            }
        }

        return numNodes;
    }
}