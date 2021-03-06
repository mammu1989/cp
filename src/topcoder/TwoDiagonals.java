import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class TwoDiagonals
{
    public int maxPoints(int[] x, int[] y) {
        int g1[] = new int[x.length];
        int cnt1[] = new int[x.length + 1];
        int c1 = 1;
        for (int i = 0; i < x.length; i++) {
            if(g1[i] != 0) continue;
            g1[i] = c1;
            cnt1[c1]++;
            for (int j = i + 1; j < x.length; j++) {
                if(y[j] - y[i] == x[j] - x[i]) {
                    g1[j] = c1;
                    cnt1[c1]++;
                }
            }
            c1++;
        }
        int g2[] = new int[x.length];
        int c2 = 1;
        for (int i = 0; i < x.length; i++) {
            if(g2[i] != 0) continue;
            g2[i] = c2;
            for (int j = i + 1; j < x.length; j++) {
                if(y[j] - y[i] == x[i] - x[j]) {
                    g2[j] = c2;
                }
            }
            c2++;
        }

        int max = 0;
        for (int i = 1; i < c1; i++) {
            int cnt2[] = new int[x.length + 1];
            int mxCnt = 0;
            for (int j = 0; j < x.length; j++) {
                if(g1[j] == i) continue;
                cnt2[g2[j]]++;
                mxCnt = Math.max(mxCnt, cnt2[g2[j]]);
            }
            max = Math.max(max, mxCnt + cnt1[i]);
        }

        return max;
    }
	
	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, int[] p0, int[] p1, boolean hasAnswer, int p2) {
		System.out.print("Test " + testNum + ": [" + "{");
		for (int i = 0; p0.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p0[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p1.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p1[i]);
		}
		System.out.print("}");
		System.out.println("]");
		TwoDiagonals obj;
		int answer;
		obj = new TwoDiagonals();
		long startTime = System.currentTimeMillis();
		answer = obj.maxPoints(p0, p1);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + p2);
		}
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		if (hasAnswer) {
			res = answer == p2;
		}
		if (!res) {
			System.out.println("DOESN'T MATCH!!!!");
		} else if ((endTime - startTime) / 1000.0 >= 2) {
			System.out.println("FAIL the timeout");
			res = false;
		} else if (hasAnswer) {
			System.out.println("Match :-)");
		} else {
			System.out.println("OK, but is it right?");
		}
		System.out.println("");
		return res;
	}
	public static void main(String[] args) {
		boolean all_right;
		all_right = true;
		
		int[] p0;
		int[] p1;
		int p2;
		
		// ----- test 0 -----
		p0 = new int[]{1,4,4,5};
		p1 = new int[]{3,0,2,3};
		p2 = 4;
		all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = new int[]{0,1,2,3,4,5};
		p1 = new int[]{2,2,2,2,2,2};
		p2 = 2;
		all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = new int[]{2,2,3,3};
		p1 = new int[]{2,3,2,3};
		p2 = 4;
		all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = new int[]{10,0,15,9};
		p1 = new int[]{10,0,15,11};
		p2 = 4;
		all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = new int[]{273,100,999,789,105};
		p1 = new int[]{838,200,999,0,560};
		p2 = 2;
		all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 5 -----
		p0 = new int[]{0,2,0,2,1};
		p1 = new int[]{0,0,2,2,1};
		p2 = 5;
		all_right = KawigiEdit_RunTest(5, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 6 -----
		p0 = new int[]{500,503,501};
		p1 = new int[]{200,197,199};
		p2 = 3;
		all_right = KawigiEdit_RunTest(6, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 7 -----
		p0 = new int[]{0,2,4};
		p1 = new int[]{0,3,6};
		p2 = 2;
		all_right = KawigiEdit_RunTest(7, p0, p1, true, p2) && all_right;
		// ------------------
		
		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
	// END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
