package codeforces;

import java.util.Scanner;

public class R493PC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long x = scanner.nextInt();
        long y = scanner.nextInt();
        String s = scanner.next();
        int c = 0;
        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == '0') {
                while (i < n && s.charAt(i) == '0') {
                    i++;
                }
                i--;
                c++;
            }
        }

        System.out.println(c == 0 ? 0 : x < y ? x * (c - 1) + y : y * c);
    }
}