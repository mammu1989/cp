import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class LongJumpCompetition
{
    public class Runner implements Comparable<Runner> {
        int bib;
        int r1 = -1, r2 = -1, r3 = -1;

        public Runner(int bib) {
            this.bib = bib;
        }

        @Override
        public int compareTo(Runner o) {
            int x1[] = new int[]{r1, r2, r3};
            int x2[] = new int[]{o.r1, o.r2, o.r3};
            Arrays.sort(x1);
            Arrays.sort(x2);
            return x1[2] != x2[2] ? x1[2] - x2[2]
                    : x1[1] != x2[1] ? x1[1] - x2[1]
                    : x1[0] != x2[0] ? x1[0] - x2[0] : o.bib - bib;
        }
    }

    public int[] recoverStandings(int[] jumpLengths) {
        int n = jumpLengths.length / 3;
        Runner runners[] = new Runner[n];
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            runners[i] = new Runner(i);
        }
        Arrays.sort(runners);
        for (int i = 0; i < n; i++) runners[i].r1 = jumpLengths[i];
        Arrays.sort(runners);
        for (int i = 0; i < n; i++) runners[i].r2 = jumpLengths[i + n];
        Arrays.sort(runners);
        for (int i = 0; i < n; i++) runners[i].r3 = jumpLengths[i + 2 * n];
        Arrays.sort(runners);
        for (int i = 0; i < n; i++) ans[i] = runners[n-1-i].bib;

        return ans;
    }	
    
	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, int[] p0, boolean hasAnswer, int[] p1) {
		System.out.print("Test " + testNum + ": [" + "{");
		for (int i = 0; p0.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p0[i]);
		}
		System.out.print("}");
		System.out.println("]");
		LongJumpCompetition obj;
		int[] answer;
		obj = new LongJumpCompetition();
		long startTime = System.currentTimeMillis();
		answer = obj.recoverStandings(p0);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.print("\t" + "{");
			for (int i = 0; p1.length > i; ++i) {
				if (i > 0) {
					System.out.print(",");
				}
				System.out.print(p1[i]);
			}
			System.out.println("}");
		}
		System.out.println("Your answer:");
		System.out.print("\t" + "{");
		for (int i = 0; answer.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(answer[i]);
		}
		System.out.println("}");
		if (hasAnswer) {
			if (answer.length != p1.length) {
				res = false;
			} else {
				for (int i = 0; answer.length > i; ++i) {
					if (answer[i] != p1[i]) {
						res = false;
					}
				}
			}
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
		
		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
	// END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!