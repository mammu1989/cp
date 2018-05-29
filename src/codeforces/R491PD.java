package codeforces;

import java.util.Scanner;

public class R491PD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        int dp[][] = new int[s1.length()][4];

        for(int i = s1.length()-2; i >= 0; i--) {
            if(s1.charAt(i) == '0') {
                if(s2.charAt(i) == '0') {
                    dp[i][0] = dp[i+1][1] + dp[i+1][2];
                } else {
                    dp[i][2] = dp[i+1][0];
                }
            } else {
                if(s2.charAt(i) == '0') {
                    dp[i][1] = dp[i+1][0] + dp[i+1][2];
                }
            }
        }
    }
}
