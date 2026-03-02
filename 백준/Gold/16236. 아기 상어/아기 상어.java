import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int LIMIT = Integer.MAX_VALUE;
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static Point start;
    static int totalTime = 0;
    static int size = 2;
    static int exp = 0;
    static Queue<Point> queue = null;
    static final int[][] directions = new int[][]{
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1}
    };

    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", r, c);
        }
    }

    public static Point bfs(Point start) {
        int time = 0;
        visited = new boolean[N][N];
        visited[start.r][start.c] = true;
        queue = new LinkedList<>();
        queue.add(start);

        Point curr;
        int r, c, nr, nc, qs;
        int targetR = LIMIT;
        int targetC = LIMIT;
        while (!queue.isEmpty()) {
            qs = queue.size();
            while (qs > 0) {
                curr = queue.poll();
                --qs;
                r = curr.r;
                c = curr.c;

                for (int i = 0; i < 4; ++i) {
                    nr = r + directions[i][0];
                    nc = c + directions[i][1];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }
                    if (visited[nr][nc]) {
                        continue;
                    }
                    if (board[nr][nc] > size) {
                        continue;
                    }

                    visited[nr][nc] = true;
                    if (board[nr][nc] > 0 && board[nr][nc] < size) {
                        if (nr < targetR || (nr == targetR && nc < targetC)) {
                            targetR = nr;
                            targetC = nc;
                        }
                        continue;
                    }
                    queue.add(new Point(nr, nc));
                }
            }
            ++time;
            if (targetR != LIMIT) {
                break;
            }
        }
        if (targetR == LIMIT) {
            return null;
        }
        board[targetR][targetC] = 0;
        exp++;
        if (exp == size) {
            size++;
            exp = 0;
        }
        totalTime += time;
//        System.out.printf("%s -> %s, size: %d, time: %d\n", start, new Point(targetR, targetC), size, totalTime);
        return new Point(targetR, targetC);
    }

    public static void main(String[] _s) throws Exception {
        StringTokenizer tokenizer = null;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int r = 0; r < N; ++r) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; ++c) {
                board[r][c] = Integer.parseInt(tokenizer.nextToken());
                if (board[r][c] == 9) {
                    board[r][c] = 0;
                    start = new Point(r, c);
                }
            }
        }

        Point curr = start;
        do {
            curr = bfs(curr);
        } while (curr != null);

        bw.write(Integer.toString(totalTime));

        bw.flush();
        bw.close();
        br.close();
    }

}
