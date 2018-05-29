package codeforces;

import java.util.Arrays;
import java.util.Scanner;

public class R492PB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int a[] = new int[n];
        float s = 0;
        for(int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            s += a[i];
        }

        Arrays.sort(a);

        if((s / n) >= 4.5) {
            System.out.println(0);
        } else {
            for(int i = 0; i < n; i++) {
                s = s + (5-a[i]);
                if((s / n) >= 4.5) {
                    System.out.println(i+1);
                    break;
                }
            }
        }
    }
}
