package topcoder;

public class TileFlippingGame {
    public int HowManyMoves(int n, int m, String[] X) {
        int minMoves = Integer.MAX_VALUE;

        for(int i = 0; i < 2; i++) {
            char ch = i == 0 ? 'w' : 'b';

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    minMoves = Math.min(minMoves, getMinMoves(ch, X, j, k, n, m));
                }
            }
        }

        return 1;
    }

    private int getMinMoves(char ch, String[] x, int j, int k, int n, int m) {
        return -1;
    }
}