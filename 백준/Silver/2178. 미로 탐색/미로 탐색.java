import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    final static int[][] DIRECTIONS = new int[][]{
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    public static class Point {
        int r;
        int c;
        int dist;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
            dist = 0;
        }

        public Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        public void addDistance() {
            this.dist++;
        }

        public Point add(int r, int c) {
            return new Point(this.r + r, this.c + c, this.dist + 1);
        }

        @Override
        public String toString() {
            return String.format("(%d,%d) = %d", r, c, dist);
        }
    }

    public static boolean canGo(Point point, int N, int M) {
        return point.r >= 0 && point.c >= 0 && point.r < N && point.c < M;
    }

    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tok = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(tok.nextToken());
        final int M = Integer.parseInt(tok.nextToken());

        final int[][] maze = new int[N][M];
        final boolean[][] visited = new boolean[N][M];
        String line;
        for (int n = 0; n < N; ++n) {
            line = br.readLine();
            for (int m = 0; m < M; ++m) {
                maze[n][m] = line.charAt(m) - 48;
            }
        }

        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0, 1));
        Point curr = null;
        int size = 0;
        int dist = 0;
        while (!queue.isEmpty()) {
            if (size == 0) {
                ++dist;
                size = queue.size();
            }
            curr = queue.poll();
            --size;
            if (curr.r == N - 1 && curr.c == M - 1) {
                break;
            }
            if (visited[curr.r][curr.c] || maze[curr.r][curr.c] == 0) {
                continue;
            }
//            System.out.println(curr);

            visited[curr.r][curr.c] = true;
            Point next;
            for (int i = 0; i < 4; ++i) {
                next = curr.add(DIRECTIONS[i][0], DIRECTIONS[i][1]);
                if (canGo(next, N, M)) {
                    queue.add(next);
                }
            }
        }
        bw.write(Integer.toString(dist));

        // close
        bw.close();
        br.close();
    }
}
