import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class TheSquareCityDiv2
{
	public int[] find(int r, int[] t)
	{
	    int g[][] = new int[t.length][t.length];
		int n = (int)Math.sqrt(t.length);
		int maxPeople = 0;
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
			    int maxT = t[i*n+j];
			    int mx = i;
			    int my = j;
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if(maxT < t[k*n+l] && Math.abs(i-k) + Math.abs(j-l) <= r) {
                            mx = k;
                            my = l;
                            maxT = t[k*n+l];
                        }
                    }
                }
                if(i != mx || j != my) {
                    g[mx*n+my][i*n+j] = g[i*n+j][mx*n+my] = 1;
                }
			}
		}

		Queue<Integer> q = new LinkedList<>();
		boolean isVisited[] = new boolean[t.length];
		Arrays.fill(isVisited, false);
        for (int i = 0; i < t.length; i++) {
            if(!isVisited[i]) {
                cnt++;
                int cntPeople = 0;
                q.offer(i);
                isVisited[i] = true;
                while (!q.isEmpty()) {
                    int p = q.poll();
                    cntPeople++;
                    for (int j = 0; j < g.length; j++) {
                        if(p == j || g[j][p] == 0) continue;
                        if(!isVisited[j]) {
                            q.offer(j);
                            isVisited[j] = true;
                        }
                    }
                }
                maxPeople = Math.max(maxPeople, cntPeople);
            }
        }

		return new int[]{cnt, maxPeople};
	}

	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, int p0, int[] p1, boolean hasAnswer, int[] p2) {
		System.out.print("Test " + testNum + ": [" + p0 + "," + "{");
		for (int i = 0; p1.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p1[i]);
		}
		System.out.print("}");
		System.out.println("]");
		TheSquareCityDiv2 obj;
		int[] answer;
		obj = new TheSquareCityDiv2();
		long startTime = System.currentTimeMillis();
		answer = obj.find(p0, p1);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.print("\t" + "{");
			for (int i = 0; p2.length > i; ++i) {
				if (i > 0) {
					System.out.print(",");
				}
				System.out.print(p2[i]);
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
			if (answer.length != p2.length) {
				res = false;
			} else {
				for (int i = 0; answer.length > i; ++i) {
					if (answer[i] != p2[i]) {
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
		
		int p0;
		int[] p1;
		int[] p2;
		
		// ----- test 0 -----
		p0 = 1;
		p1 = new int[]{9,1,6,5,3,2,7,4,8};
		p2 = new int[]{4,4};
		all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = 2;
		p1 = new int[]{9,1,6,5,3,2,7,4,8};
		p2 = new int[]{2,6};
		all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = 7;
		p1 = new int[]{9,1,6,5,3,2,7,4,8};
		p2 = new int[]{1,9};
		all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = 3;
		p1 = new int[]{59,22,2,17,77,43,67,16,49,51,46,61,4,9,42,12,80,82,24,29,1,27,63,65,26,10,28,83,7,73,8,47,37,23,38,75,54,71,58,78,21,45,35,81,48,41,44,52,32};
		p2 = new int[]{5,20};
		all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
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
