package codeforces.r494;

import java.util.Scanner;

public class R494PC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int c[] = new int[n + 1];

        for (int i = 0; i < n; i++) {
            c[i + 1] = c[i] + a[i];
        }

        double intensity = 0;

        for (int i = k; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                intensity = Math.max(intensity, (1.0 * (- c[j] + c[j+i])) / i);
            }
        }

        System.out.println(intensity);
    }
}
