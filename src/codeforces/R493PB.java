package codeforces;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class R493PB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int bitCoins = scanner.nextInt();
        int a[] = new int[n];
        int odds[] = new int[n];
        a[0] = scanner.nextInt();
        odds[0] = a[0] % 2 == 0 ? 0 : 1;
        List<Integer> l = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            a[i] = scanner.nextInt();
            odds[i] = odds[i - 1];
            if(a[i] % 2 == 1) {
                odds[i]++;
            }
        }
        for (int i = 1; i < n; i++) {
            if((i != n - 1) && ((i + 1) == 2 * odds[i])) {
                l.add(Math.abs(a[i + 1] - a[i]));
            }
        }
        l.sort(Integer::compareTo);
        int ans = 0;
        for (Integer i : l) {
            if(i <= bitCoins) {
                bitCoins -= i;
                ans++;
            }
        }
        System.out.println(ans);
    }
}
