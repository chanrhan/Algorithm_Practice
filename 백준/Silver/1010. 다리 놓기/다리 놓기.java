import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;

    public static void main(String[] _s) throws Exception {
        int T = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = null;
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[30][30];
        for (int m = 0; m < 30; ++m) {
            dp[0][m] = 1;
            for (int n = 1; n <= m; ++n) {
                dp[n][m] = dp[n - 1][m - 1] + dp[n][m - 1];
            }
        }

        for (int t = 0; t < T; ++t) {
            tokenizer = new StringTokenizer(br.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            sb.append(dp[N][M]).append('\n');
        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

}

