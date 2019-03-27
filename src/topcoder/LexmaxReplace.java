import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class LexmaxReplace
{
	public String get(String s, String t)
	{
		char sa[] = s.toCharArray();
		char ta[] = t.toCharArray();
		Arrays.sort(ta);
		for (int i = 0; i < ta.length; i++) {
			for (int j = 0; j < sa.length; j++) {
				if(ta[ta.length - i - 1] > sa[j]) {
					sa[j] = ta[ta.length - i - 1];
					break;
				}
			}
		}

		return new String(sa);
	}
	
	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, String p0, String p1, boolean hasAnswer, String p2) {
		System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"" + "," + "\"" + p1 + "\"");
		System.out.println("]");
		LexmaxReplace obj;
		String answer;
		obj = new LexmaxReplace();
		long startTime = System.currentTimeMillis();
		answer = obj.get(p0, p1);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + "\"" + p2 + "\"");
		}
		System.out.println("Your answer:");
		System.out.println("\t" + "\"" + answer + "\"");
		if (hasAnswer) {
			res = answer.equals(p2);
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
		
		String p0;
		String p1;
		String p2;
		
		// ----- test 0 -----
		p0 = "abb";
		p1 = "c";
		p2 = "cbb";
		all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = "z";
		p1 = "f";
		p2 = "z";
		all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = "fedcba";
		p1 = "ee";
		p2 = "feeeba";
		all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = "top";
		p1 = "coder";
		p2 = "trp";
		all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = "xldyzmsrrwzwaofkcxwehgvtrsximxgdqrhjthkgfucrjdvwlr";
		p1 = "xfpidmmilhdfzypbguentqcojivertdhshstkcysydgcwuwhlk";
		p2 = "zyyyzyxwwwzwvuuttxwtssvtssxrqxppqrontmmllukrkjvwlr";
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