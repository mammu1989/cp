import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class MagicNumbersAgain
{
	public int count(long A, long B)
	{
		int cnt = 0;
		for (long i = 1; i*i <= B; i++) {
			if(i*i < A) continue;

			if(isMagic(i*i)) {
				cnt++;
			}
		}

		return cnt;
	}

	private boolean isMagic(long l) {
		String num = String.valueOf(l);
        for (int i = 0; i < num.length() - 1; i++) {
            if(i % 2 == 0) {
                if(num.charAt(i) >= num.charAt(i + 1)) {
                    return false;
                }
            } else {
                if(num.charAt(i) <= num.charAt(i + 1)) {
                    return false;
                }
            }
        }

		return true;
	}

	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, long p0, long p1, boolean hasAnswer, int p2) {
		System.out.print("Test " + testNum + ": [" + p0 + "," + p1);
		System.out.println("]");
		MagicNumbersAgain obj;
		int answer;
		obj = new MagicNumbersAgain();
		long startTime = System.currentTimeMillis();
		answer = obj.count(p0, p1);
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
		
		long p0;
		long p1;
		int p2;
		
		// ----- test 0 -----
		p0 = 1L;
		p1 = 64L;
		p2 = 7;
		all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = 50L;
		p1 = 60L;
		p2 = 0;
		all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = 121L;
		p1 = 121L;
		p2 = 1;
		all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = 5679L;
		p1 = 1758030L;
		p2 = 73;
		all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = 1304164L;
		p1 = 2000000L;
		p2 = 14;
		all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
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
