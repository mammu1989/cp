package topcoder;

import java.util.Arrays;

public class TreesAndBrackets {
    public String check(String t1, String t2) {
        int a[] = getArray(t1);
        int b[] = getArray(t2);

        System.out.println(tostring(a));
        System.out.println(tostring(b));
        System.out.println(lcs(a,b));
        return lcs(a,b) == b.length ? "Possible" : "Impossible";
    }

    private String tostring(int[] a) {
        StringBuilder sb = new StringBuilder();

        for(int i : a) {
            sb.append(i);
        }

        return sb.toString();
    }

    private int lcs(int[] a, int[] b) {
        int dp[][] = new int[a.length][b.length];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return f(a,b,dp,0,0);
    }

    private int f(int[] a, int[] b, int[][] dp, int i, int j) {
        if(i == a.length || j == b.length) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        if(a[i] == b[j]) dp[i][j] = f(a,b,dp,i+1,j+1)+1;
        int v = Math.max(f(a,b,dp,i+1,j), f(a,b,dp,i,j+1));

        return Math.max(v,dp[i][j]);
    }


    private int[] getArray(String t1) {
        int a[] = new int[t1.length()];
        int c1 = 0;

        for(int i = 0; i < t1.length(); i++) {
            if(t1.charAt(i) == '(') c1++;
            else c1--;
            a[i] = c1;
        }

        return a;
    }

    public static void main(String[] args) {
        TreesAndBrackets o = new TreesAndBrackets();

        System.out.println(o.check("(((()))(()))", "(((())()))"));
    }
}
