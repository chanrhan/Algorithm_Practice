import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static final int[][] dir = new int[][]{
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
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

    public static int get(Point p, int[][] map) {
        return map[p.r][p.c];
    }

    public static void set(Point p, int[][] map) {
        map[p.r][p.c] = -1;
    }

    public static boolean isAvailable(Point p, int N) {
        return p.r >= 0 && p.c >= 0 && p.r < N && p.c < N;
    }

    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tk = null;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        Stack<Point> points = new Stack<>();

        int a;
        String line;
        for (int i = 0; i < N; ++i) {
            line = br.readLine();
            for (int j = 0; j < N; ++j) {
                a = line.charAt(j) - 48;
                map[i][j] = a;
                if (a == 1) {
                    points.push(new Point(i, j));
                }
            }
        }

        Stack<Point> stack = new Stack<>();
        List<Integer> results = new ArrayList<>();

        Point curr, next;
        int count;
        int total = 0;
        while (!points.isEmpty()) {
            stack.push(points.pop());

            count = 0;
            while (!stack.isEmpty()) {
                curr = stack.pop();
                if (get(curr, map) != 1) {
                    continue;
                }
//                System.out.println(curr);
                ++count;
                set(curr, map);

                for (int i = 0; i < 4; ++i) {
                    next = new Point(curr.r + dir[i][0], curr.c + dir[i][1]);
                    if (isAvailable(next, N) && get(next, map) == 1) {
                        stack.push(next);
                    }
                }
            }
            if (count > 0) {
                ++total;
                results.add(count);
            }
        }
        results.sort((c1, c2) -> {
            return c1 - c2;
        });

        bw.write(Integer.toString(total) + '\n');

        for (Integer res : results) {
            bw.write(Integer.toString(res) + '\n');
        }

        // close
        bw.close();
        br.close();
    }
}
