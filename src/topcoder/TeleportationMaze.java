import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class TeleportationMaze
{
	int x,y;
	int isVisited[][];
	int dx[] = new int[]{0,0,1,-1};
	int dy[] = new int[]{1,-1,0,0};

	class Node {
		int r;
		int c;
		int cost;

		public Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
    }

	public int pathLength(String[] a, int r1, int c1, int r2, int c2)
	{
		x = a.length;
		y = a[0].length();
		isVisited = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            isVisited[i] = new int[a[0].length()];
            Arrays.fill(isVisited[i], Integer.MAX_VALUE);
        }

		Queue<Node> pq = new LinkedList<>();

		pq.offer(new Node(r1, c1, 0));
		isVisited[r1][c1] = 0;

		while (!pq.isEmpty()) {
			Node p = pq.poll();
			for (int i = 0; i < 4; i++) {
				int x = p.r + dx[i];
				int y = p.c + dy[i];
				if(isValid(x, y) && a[x].charAt(y) == '.') {
				    if(isVisited[x][y] > p.cost + 1) {
                        isVisited[x][y] = p.cost + 1;

                        pq.offer(new Node(x, y, p.cost + 1));
                    }
				} else {
				    for(x = x + dx[i], y = y + dy[i]; isValid(x, y); x = x + dx[i], y = y + dy[i]) {
						if(a[x].charAt(y) == '.') {
							if(isVisited[x][y] > p.cost + 2) {
							    isVisited[x][y] = p.cost + 2;

								pq.offer(new Node(x, y,p.cost + 2));
							}
							break;
						}
					}
				}
			}
		}

		return isVisited[r2][c2] == Integer.MAX_VALUE ? -1 : isVisited[r2][c2];
	}

	public boolean isValid(int r, int c) {
		return r >= 0 && r < x && c >= 0 && c < y;
	}
	

}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
