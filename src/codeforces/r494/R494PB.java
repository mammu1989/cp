package codeforces.r494;

import java.util.Scanner;

public class R494PB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int x = scanner.nextInt();
        char ch1 = a > b ? '0' : '1';
        char ch2 = a > b ? '1' : '0';
        char c[] = new char[a+b];

        for (int i = 0; i < a + b; i++) {
            c[i] = ch1;
        }

        int st = 0;
        for (int i = 0; i < x - 1; i++) {
            c[2 * i + 1] = ch2;
            st = 2 * (i + 1);
        }

        int rem = Math.min(a,b) - (x > 2 ? x - 2 : 0);
        for (int i = st; i < st + rem; i++) {
            c[i] = ch2;
        }

        System.out.println(new String(c) + " " + ch1 + " " + ch2);
    }
}
