package codeforces;

import java.util.Scanner;

public class R493PA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a[] = new int[n];
        int min = Integer.MAX_VALUE;
        int ind = -1;
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            if(a[i] < min) {
                min = a[i];
                ind = i;
            }
        }

        if(n == 1) {
            System.out.println(-1);
            return;
        } else if(n == 2 && a[0] == a[1]) {
            System.out.println(-1);
            return;
        }
        System.out.println("1 " + (ind + 1));
    }
}
