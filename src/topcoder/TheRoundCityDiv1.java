package topcoder;

import java.util.ArrayList;

public class TheRoundCityDiv1 {
    public long find(int r) {
        long cnt = 1;

        int pf[] = getPrimeFactors(r);

        for(int i = 1; i <= r; i++) {
            cnt = cnt + getPointCnts(i, r, pf);
        }

        return cnt * 4;
    }

    private int[] getPrimeFactors(int n) {
        int pf[] = new int[n+1];

        for(int i = 2; i <= n; i++) {
            if(pf[i] == 0) {
                for(int j = i; j <= n; j += i) {
                    if(pf[j] == 0) {
                        pf[j] = i;
                    }
                }
            }
        }

        pf[1] = 1;

        return pf;
    }

    private long getPointCnts(long x, long r, int[] pf) {
        ArrayList<Integer> p = getAllPF(x, pf);
        int y = (int)Math.floor(Math.sqrt(r*r - x*x));
        long cnt = 0;
        int pfCount = p.size();
        for(int i = 0; i < (1<<pfCount); i++) {
            int sign = 1;
            long pro = 1;
            for(int j = 0; j < pfCount; j++) {
                if((i&(1<<j)) != 0) {
                    sign = -sign;
                    pro *= p.get(j);
                }
            }

            cnt += (sign * (y / pro));
        }

        return cnt;
    }

    private ArrayList<Integer> getAllPF(long v, int pf[]) {
        ArrayList<Integer> p = new ArrayList<>();

        while (pf[(int)v] != 1) {
            int pfv = pf[(int)v];
            if(v % pfv == 0) {
                p.add(pfv);
            }
            while (v % pfv == 0) {
                v = v / pfv;
            }
        }

        return p;
    }

    public static void main(String[] args) {
        TheRoundCityDiv1 o = new TheRoundCityDiv1();
        long st = System.currentTimeMillis();
//        for(int i = 25000; i < 50000; i++)
        System.out.println(46341 + " " + o.find(1000000) + " " + (System.currentTimeMillis() - st));
    }
}
