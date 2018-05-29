package codeforces;

import java.util.Scanner;

public class R491PA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int n = scanner.nextInt();
        int s = a + b - c;
        if (s <= n - 1 && s >= 0 && c <= a && c <= b) {
            System.out.println(n - s);
        } else {
            System.out.println(-1);
        }
    }
}
