import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();

        long s = 1, e = n / 2 + n % 2, m = (s + e) / 2;
        System.out.println(getMin(s, e, m, n, e));
//        System.out.println(1l<<62);
    }

    private static long getMin(long s, long e, long m, long n, long min) {
//        System.out.println(s + " " + e);
        if(e - s <= 1) {
            if(getCount(s, n) >= min) {
                return s;
            }
            return e;
        }

        if(getCount(m, n) >= min) {
            long diff = (m - s) / 2;
            return getMin(s, m, s + diff, n, min);
        } else {
            long diff = (e - m - 1) / 2;
            return getMin(m + 1, e, m + 1 + diff, n, min);
        }
    }

    private static long getCount(long k, long n) {
        if(n < 10) return n;

        long e = Math.min(n, k);
        return getCount(k, n - ((n - e) / 10) - e) + e;
    }
}
