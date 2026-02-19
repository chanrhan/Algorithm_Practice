import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K;
    static int[] weights, values;
    static int[][] dp;

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        weights = new int[N + 1];
        values = new int[N + 1];
        dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(tokenizer.nextToken());
            values[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 1; i <= N; ++i) {
            for (int w = 1; w <= K; ++w) {
                if (w - weights[i] < 0) {
                    dp[i][w] = dp[i - 1][w];
                    continue;
                }
                dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i]] + values[i]);
            }
        }

        bw.write(Integer.toString(dp[N][K]));

        bw.close();
        br.close();
    }
}
