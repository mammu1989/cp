package topcoder;

public class MinimizeAbsoluteDifferenceDiv1 {
    public int[] findTuple(int[] x) {
        boolean []isUsed = new boolean[5];
        int ans[] = new int[4];
        int num[] = new int[4];

        getMin(x, ans, num, isUsed, 0, Integer.MAX_VALUE);

        return ans;
    }

    private double getMin(int[] x, int[] ans, int[] num, boolean[] isUsed, int count, double minValue) {
        if(count == 4) {
            double candidate = (num[0]/(double)num[1]) - (num[2]/(double)num[3]);

            if(minValue > candidate) {
                ans[0] = num[0]; ans[1] = num[1]; ans[2] = num[2]; ans[3] = num[3];
                return candidate;
            }
        }

        return 0;
    }
}
