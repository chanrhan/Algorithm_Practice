import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static final int[][] directions = new int[][]{
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static class Point {
        int r;
        int c;
        int time;
        boolean broken = false;

        public Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        public Point(int r, int c, int time, boolean broken) {
            this(r, c, time);
            this.broken = broken;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", r, c);
        }
    }

    public static void bfs() {
        int[][][] log = new int[N][M][2];
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < M; ++c) {
                log[r][c][0] = Integer.MAX_VALUE;
                log[r][c][1] = Integer.MAX_VALUE;
            }
        }
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0, 1));

        Point curr;
        int r, c, nextRow, nextCol, isBroken;
        while (!queue.isEmpty()) {
            curr = queue.poll();
//            System.out.println(curr);
            r = curr.r;
            c = curr.c;
            isBroken = curr.broken ? 1 : 0;
            if (r == N - 1 && c == M - 1) {
                min = Math.min(min, curr.time);
                continue;
            }
            if (curr.time >= log[r][c][isBroken]) {
                continue;
            }
            log[r][c][isBroken] = curr.time;
            for (int i = 0; i < 4; ++i) {
                nextRow = r + directions[i][0];
                nextCol = c + directions[i][1];
                if (nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M) {
                    if (map[nextRow][nextCol] == 0) {
                        queue.add(new Point(nextRow, nextCol, curr.time + 1, curr.broken));
                    } else if (!curr.broken) {
                        queue.add(new Point(nextRow, nextCol, curr.time + 1, true));
                    }
                }
            }
        }
    }

    public static void main(String[] _s) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        char[] chars;
        for (int r = 0; r < N; ++r) {
            chars = br.readLine().toCharArray();
            for (int c = 0; c < M; ++c) {
                map[r][c] = chars[c] - '0';
            }
        }
        bfs();
        bw.write(Integer.toString(min == Integer.MAX_VALUE ? -1 : min));

        bw.close();
        br.close();
    }

}
