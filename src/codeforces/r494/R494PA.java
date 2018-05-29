package codeforces.r494;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a[] = new int[101];
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            a[num]++;
            max = Math.max(a[num], max);
        }

        System.out.println(max);
    }
}
