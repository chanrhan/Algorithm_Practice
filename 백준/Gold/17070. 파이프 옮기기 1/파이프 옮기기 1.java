import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] grid;
    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    static final int CROSS = 2;
    static int count = 0;

    public static void dp(int r, int c, int shape) {
        if (r == N - 1 && c == N - 1) {
            ++count;
            return;
        }
        if (shape != HORIZONTAL && r + 1 < N && grid[r + 1][c] == 0) {
            // 아래로 밀기
            dp(r + 1, c, VERTICAL);
        }
        if (shape != VERTICAL && c + 1 < N && grid[r][c + 1] == 0) {
            // 아래로 밀기
            dp(r, c + 1, HORIZONTAL);
        }
        if (r + 1 < N && c + 1 < N && grid[r + 1][c] == 0 && grid[r][c + 1] == 0 && grid[r + 1][c + 1] == 0) {
            // 아래로 밀기
            dp(r + 1, c + 1, CROSS);
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
        dp(0, 1, HORIZONTAL);
        bw.write(Integer.toString(count));

        bw.close();
        br.close();
    }
}
