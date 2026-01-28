import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int H, N, M;

    final static int[] dirH = new int[]{1, -1, 0, 0, 0, 0};
    final static int[] dirR = new int[]{0, 0, 1, 0, -1, 0};
    final static int[] dirC = new int[]{0, 0, 0, 1, 0, -1};

    public static class Point {
        int r;
        int c;
        int h;

        public Point(int h, int r, int c) {
            this.r = r;
            this.c = c;
            this.h = h;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,%d)", h, r, c);
        }
    }

    public static boolean isAvailable(Point p) {
        return p.h >= 0 && p.r >= 0 && p.c >= 0 && p.h < H && p.r < N && p.c < M;
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        H = Integer.parseInt(tokenizer.nextToken());

        int[][][] box = new int[H][N][M];
        boolean[][][] visited = new boolean[H][N][M];

        Queue<Point> queue = new ArrayDeque<>();
        int t;
        int totalTomatoes = 0;
        for (int h = 0; h < H; ++h) {

            for (int r = 0; r < N; ++r) {
                tokenizer = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; ++c) {
                    t = Integer.parseInt(tokenizer.nextToken());
                    box[h][r][c] = t;
                    if (t == 1) {
                        queue.add(new Point(h, r, c));
                    }
                    if (t != -1) {
                        ++totalTomatoes;
                    }
                }
            }
        }

        if (queue.size() == H * M * N) {
            bw.write("0\n");
        } else {
            Point curr, next;
            int size;
            int count;
            int day = -1;
            while (!queue.isEmpty()) {
                size = queue.size();
                count = 0;
                while (size > 0) {
                    curr = queue.poll();
                    --size;
                    if (visited[curr.h][curr.r][curr.c]) {
                        continue;
                    }
                    --totalTomatoes;

//                    System.out.println(curr);

                    visited[curr.h][curr.r][curr.c] = true;
                    box[curr.h][curr.r][curr.c] = 1;
                    ++count;
                    for (int i = 0; i < 6; ++i) {
                        next = new Point(curr.h + dirH[i], curr.r + dirR[i], curr.c + dirC[i]);
                        if (isAvailable(next) && box[next.h][next.r][next.c] == 0) {
                            queue.add(next);
                        }
                    }
                }
//                System.out.printf("count: %d, total: %d\n", count, totalTomatoes);
                if (count > 0) {
                    ++day;
                }
            }
            if (totalTomatoes > 0) {
                bw.write("-1\n");
            } else {
                bw.write(Integer.toString(day));
            }
        }

        br.close();
        bw.close();
    }
}