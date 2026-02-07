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
    static int[][] arr;
    static int[][] dp;


    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        final int T = Integer.parseInt(tokenizer.nextToken());
        for (int t = 0; t < T; ++t) {
            tokenizer = new StringTokenizer(br.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            arr = new int[2][N + 1];
            dp = new int[2][N + 1];
            for (int i = 0; i < 2; ++i) {
                tokenizer = new StringTokenizer(br.readLine());
                for (int n = 1; n <= N; ++n) {
                    arr[i][n] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            // solve

            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];
            for (int i = 2; i <= N; ++i) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
            }
            bw.write(Integer.toString(Math.max(dp[0][N], dp[1][N])) + '\n');
        }

        bw.close();
        br.close();
    }
}
