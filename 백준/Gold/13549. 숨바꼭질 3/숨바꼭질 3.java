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
    static int N, K;
    static int[] visited = new int[100001];

    public static class Point {
        int x;
        int time;

        public Point(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    public static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(N, 1));
        visited[N] = 1;

        Point curr;
        int x, t;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            x = curr.x;
            t = curr.time;
            if (x + 1 <= 100000) {
                if (visited[x + 1] == 0 || t < visited[x + 1]) {
                    visited[x + 1] = t + 1;
                    queue.add(new Point(x + 1, t + 1));
                }
            }
            if (x - 1 >= 0) {
                if (visited[x - 1] == 0 || t < visited[x - 1]) {
                    visited[x - 1] = t + 1;
                    queue.add(new Point(x - 1, t + 1));
                }
            }
            if (x * 2 <= 100000 && x * 2 >= 0) {
                if (visited[x * 2] == 0 || t < visited[x * 2]) {
                    visited[x * 2] = t;
                    queue.add(new Point(x * 2, t));
                }
            }
        }
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        bfs();
        bw.write(Integer.toString(visited[K] - 1));

        bw.close();
        br.close();
    }
}
