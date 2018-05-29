package topcoder;

public class PalindromeSubsequence {
    public int[] optimalPartition(String s) {
        int a[] = new int[s.length()];

        if(isPalin(s)) {
            for(int i = 0; i < a.length; i++) {
                a[i] = 1;
            }
        }

        for(int i = 0; i < a.length; i++) {
            if(s.charAt(i) == s.charAt(0)) {
                a[i] = 1;
            } else {
                a[i] = 2;
            }
        }

        return a;
    }

    private boolean isPalin(String s) {
        int i = 0;
        int j = s.length()-1;

        while (i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
