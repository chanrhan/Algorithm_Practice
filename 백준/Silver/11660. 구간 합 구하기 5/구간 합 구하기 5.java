import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] arr;
    static int[][] dp;
    static int N, M;
    static int lastRow = 1, lastCol = 1;

    public static void dp(int x, int y) {
        if (dp[x][y] == 0) {
            for (int r = 1; r <= x; ++r) {
                for (int c = 1; c <= y; ++c) {
                    if (dp[r][c] == 0) {
                        dp[r][c] = dp[r][c - 1] + dp[r - 1][c] + arr[r][c] - dp[r - 1][c - 1];
                    }
                }
            }
        }
    }

    public static void print() {
        System.out.println();
        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                System.out.printf("%d ", dp[r][c]);
            }
            System.out.println();
        }
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];
        for (int r = 1; r <= N; ++r) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; ++c) {
                arr[r][c] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        dp[1][1] = arr[1][1];

        int x1, x2, y1, y2;
        for (int m = 0; m < M; ++m) {
            tokenizer = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(tokenizer.nextToken());
            y1 = Integer.parseInt(tokenizer.nextToken());
            x2 = Integer.parseInt(tokenizer.nextToken());
            y2 = Integer.parseInt(tokenizer.nextToken());

            dp(x2, y2);
//            print();

            int ans = dp[x2][y2] - (dp[x1 - 1][y2] + dp[x2][y1 - 1]) + dp[x1 - 1][y1 - 1];
            bw.write(Integer.toString(ans) + '\n');
        }
        bw.close();
        br.close();
    }
}
