import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class CCChecker2
{
	public String check(int n, int[] startRow, int[] startCol, int[] destRow, int[] destCol, int[] moveStartRow, int[] moveStartCol, int[] moveDestRow, int[] moveDestCol)
	{
		int b[][] = new int[n + 1][n + 1];
		for (int i = 0; i < b.length; i++) {
			Arrays.fill(b[i], -1);
		}
		for (int i = 0; i < startCol.length; i++) {
			b[startRow[i]][startCol[i]] = i;
		}

		for (int i = 0; i < moveStartRow.length; i++) {
			if(!isValid(b, moveStartRow[i], moveStartCol[i], moveDestRow[i], moveDestCol[i], n)) {
				return "invalid";
			}
			b[moveDestRow[i]][moveDestCol[i]] = b[moveStartRow[i]][moveStartCol[i]];
			b[moveStartRow[i]][moveStartCol[i]] = -1;
		}

		for (int i = 0; i < destCol.length; i++) {
			if(b[destRow[i]][destCol[i]] != i) {
				return "invalid";
			}
		}

		return "valid";
	}

	private boolean isValid(int[][] b, int i1, int j1, int i2, int j2, int n) {
		if(!isIn(i1, n)) return false;
		if(!isIn(i2, n)) return false;
		if(!isIn(j1, n)) return false;
		if(!isIn(j2, n)) return false;
		if(b[i1][j1] == -1) return false;
		if(b[i2][j2] != -1) return false;
		if(Math.abs(i1 - i2) + Math.abs(j1 - j2) != 1) return false;

		return true;
	}

	private boolean isIn(int i, int n) {
		return i > 0 && i <= n;
	}

	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, int p0, int[] p1, int[] p2, int[] p3, int[] p4, int[] p5, int[] p6, int[] p7, int[] p8, boolean hasAnswer, String p9) {
		System.out.print("Test " + testNum + ": [" + p0 + "," + "{");
		for (int i = 0; p1.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p1[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p2.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p2[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p3.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p3[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p4.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p4[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p5.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p5[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p6.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p6[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p7.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p7[i]);
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p8.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p8[i]);
		}
		System.out.print("}");
		System.out.println("]");
		CCChecker2 obj;
		String answer;
		obj = new CCChecker2();
		long startTime = System.currentTimeMillis();
		answer = obj.check(p0, p1, p2, p3, p4, p5, p6, p7, p8);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + "\"" + p9 + "\"");
		}
		System.out.println("Your answer:");
		System.out.println("\t" + "\"" + answer + "\"");
		if (hasAnswer) {
			res = answer.equals(p9);
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
		
		int p0;
		int[] p1;
		int[] p2;
		int[] p3;
		int[] p4;
		int[] p5;
		int[] p6;
		int[] p7;
		int[] p8;
		String p9;
		
		// ----- test 0 -----
		p0 = 2;
		p1 = new int[]{1};
		p2 = new int[]{1};
		p3 = new int[]{2};
		p4 = new int[]{2};
		p5 = new int[]{1,1};
		p6 = new int[]{1,2};
		p7 = new int[]{1,2};
		p8 = new int[]{2,2};
		p9 = "valid";
		all_right = KawigiEdit_RunTest(0, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = 2;
		p1 = new int[]{1,2};
		p2 = new int[]{1,2};
		p3 = new int[]{1,2};
		p4 = new int[]{2,1};
		p5 = new int[]{2,1,2,1,2,1,2,2};
		p6 = new int[]{2,1,1,2,2,1,1,2};
		p7 = new int[]{1,2,2,1,1,2,2,2};
		p8 = new int[]{2,1,2,1,2,1,2,1};
		p9 = "valid";
		all_right = KawigiEdit_RunTest(1, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = 6;
		p1 = new int[]{};
		p2 = new int[]{};
		p3 = new int[]{};
		p4 = new int[]{};
		p5 = new int[]{};
		p6 = new int[]{};
		p7 = new int[]{};
		p8 = new int[]{};
		p9 = "valid";
		all_right = KawigiEdit_RunTest(2, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = 3;
		p1 = new int[]{1};
		p2 = new int[]{1};
		p3 = new int[]{1};
		p4 = new int[]{1};
		p5 = new int[]{-47};
		p6 = new int[]{-42};
		p7 = new int[]{125216547};
		p8 = new int[]{125216547};
		p9 = "invalid";
		all_right = KawigiEdit_RunTest(3, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = 3;
		p1 = new int[]{1};
		p2 = new int[]{1};
		p3 = new int[]{1};
		p4 = new int[]{3};
		p5 = new int[]{1};
		p6 = new int[]{1};
		p7 = new int[]{1};
		p8 = new int[]{3};
		p9 = "invalid";
		all_right = KawigiEdit_RunTest(4, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
		// ------------------
		
		// ----- test 5 -----
		p0 = 6;
		p1 = new int[]{1};
		p2 = new int[]{1};
		p3 = new int[]{2};
		p4 = new int[]{2};
		p5 = new int[]{1};
		p6 = new int[]{1};
		p7 = new int[]{2};
		p8 = new int[]{2};
		p9 = "invalid";
		all_right = KawigiEdit_RunTest(5, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
		// ------------------
		
		// ----- test 6 -----
		p0 = 3;
		p1 = new int[]{1,1};
		p2 = new int[]{1,2};
		p3 = new int[]{1,1};
		p4 = new int[]{3,2};
		p5 = new int[]{1,1};
		p6 = new int[]{1,2};
		p7 = new int[]{1,1};
		p8 = new int[]{2,3};
		p9 = "invalid";
		all_right = KawigiEdit_RunTest(6, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
		// ------------------
		
		// ----- test 7 -----
		p0 = 3;
		p1 = new int[]{1,1};
		p2 = new int[]{1,2};
		p3 = new int[]{1,1};
		p4 = new int[]{3,2};
		p5 = new int[]{1,1};
		p6 = new int[]{2,1};
		p7 = new int[]{1,1};
		p8 = new int[]{3,2};
		p9 = "invalid";
		all_right = KawigiEdit_RunTest(7, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
		// ------------------
		
		// ----- test 8 -----
		p0 = 3;
		p1 = new int[]{1,1};
		p2 = new int[]{1,2};
		p3 = new int[]{1,1};
		p4 = new int[]{3,2};
		p5 = new int[]{1,1,1,2};
		p6 = new int[]{2,1,2,2};
		p7 = new int[]{2,1,1,1};
		p8 = new int[]{2,2,3,2};
		p9 = "valid";
		all_right = KawigiEdit_RunTest(8, p0, p1, p2, p3, p4, p5, p6, p7, p8, true, p9) && all_right;
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
