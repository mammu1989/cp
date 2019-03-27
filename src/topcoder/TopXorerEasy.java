public class TopXorerEasy {
    public 	int maximalRating(int A, int B, int C) {
        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            int cnt = 0;
            if((A&(1<<i)) != 0) cnt++;
            if((B&(1<<i)) != 0) cnt++;
            if((C&(1<<i)) != 0) cnt++;
            if(cnt == 1) ans = ans | (1 << i);
            else if(cnt > 1) { ans = ans | ((1 << (i + 1)) - 1); break; }
        }

        return ans;
    }

    public static void main(String[] args) {
        TopXorerEasy topXorerEasy = new TopXorerEasy();

        System.out.println(topXorerEasy.maximalRating(2,1,0));
        System.out.println(topXorerEasy.maximalRating(1,2,4));
        System.out.println(topXorerEasy.maximalRating(3,4,5));
        System.out.println(topXorerEasy.maximalRating(1,100,10000));
        System.out.println(topXorerEasy.maximalRating(1000000000, 1000000000, 1000000000));
        System.out.println(topXorerEasy.maximalRating(0,0,0));
    }
}
