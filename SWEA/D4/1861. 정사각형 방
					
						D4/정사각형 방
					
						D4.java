import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static class Point {
	int r, c;

	public Point(int r, int c) {
	    this.r = r;
	    this.c = c;
	}
    }

    public static void main(String args[]) throws Exception {
	StringTokenizer tokenizer = null;
	StringBuilder sb = new StringBuilder();

	int T = Integer.parseInt(br.readLine());

	Point[] points;
	int[][] board;
	for (int tc = 1; tc <= T; ++tc) {
	    int N = Integer.parseInt(br.readLine());
	    board = new int[N][N];
	    points = new Point[N * N + 1];

	    for (int r = 0; r < N; ++r) {
		tokenizer = new StringTokenizer(br.readLine());
		for (int c = 0; c < N; ++c) {
		    int n = Integer.parseInt(tokenizer.nextToken());
		    points[n] = new Point(r, c);
		    board[r][c] = n;
		}
	    }

	    int i = 1;
	    Point curr;
	    int nr, nc, len;
	    int start;
	    int result = 0, maxLen = Integer.MIN_VALUE;
	    while (i < N * N + 1) {
		len = 1;
		start = i;
		while (true) {
		    curr = points[i];
//		    System.out.printf("%d", i);

		    boolean flag = false;
		    for (int k = 0; k < 4; ++k) {
			nr = curr.r + dir[k][0];
			nc = curr.c + dir[k][1];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
			    continue;
			if (board[nr][nc] - board[curr.r][curr.c] == 1) {
			    flag = true;
			    i = board[nr][nc];
			    ++len;
			    break;
			}
		    }

		    if (!flag) {
			if (len > maxLen) {
			    maxLen = len;
			    result = start;
			}
			++i;
			break;
		    }
		}

	    }

	    sb.append('#').append(tc).append(' ').append(result).append(' ').append(maxLen).append('\n');
	}
	System.out.println(sb);
    }
}
