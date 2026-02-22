import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] grid;
    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    static final int CROSS = 2;


    public static class Pipe {
        int r;
        int c;
        int shape;

        public Pipe(int r, int c, int shape) {
            this.r = r;
            this.c = c;
            this.shape = shape;
        }
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        grid = new int[N][N];
        for (int r = 0; r < N; ++r) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; ++c) {
                grid[r][c] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        ArrayDeque<Pipe> deque = new ArrayDeque<>();
        deque.add(new Pipe(0, 1, HORIZONTAL));

        Pipe curr;
        int count = 0;
        while (!deque.isEmpty()) {
            curr = deque.pop();
            if (curr.r == N - 1 && curr.c == N - 1) {
                ++count;
                continue;
            }
            if (curr.shape != HORIZONTAL && curr.r + 1 < N && grid[curr.r + 1][curr.c] == 0) {
                // 아래로 밀기
                deque.add(new Pipe(curr.r + 1, curr.c, VERTICAL));
            }
            if (curr.shape != VERTICAL && curr.c + 1 < N && grid[curr.r][curr.c + 1] == 0) {
                // 오른쪽으로 밀기
                deque.add(new Pipe(curr.r, curr.c + 1, HORIZONTAL));
            }
            if (curr.r + 1 < N && curr.c + 1 < N && grid[curr.r + 1][curr.c] == 0 && grid[curr.r][curr.c + 1] == 0
                    && grid[curr.r + 1][curr.c + 1] == 0) {
                // 오른쪽 아래로 밀기
                deque.add(new Pipe(curr.r + 1, curr.c + 1, CROSS));
            }
        }
        bw.write(Integer.toString(count));

        bw.close();
        br.close();
    }
}
