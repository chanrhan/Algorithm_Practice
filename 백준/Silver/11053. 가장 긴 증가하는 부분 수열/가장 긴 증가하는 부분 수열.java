import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] seq;
    static Integer[] dp;

    public static int LIS(int n) {
        if (dp[n] == null) {
            dp[n] = 1;
            for (int i = n - 1; i >= 0; --i) {
                if (seq[i] < seq[n]) {
                    dp[n] = Math.max(dp[n], LIS(i) + 1);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] _s) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        seq = new int[N];
        dp = new Integer[N];
        for (int i = 0; i < N; ++i) {
            seq[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < N; ++i) {
            LIS(i);
        }
        int max = 0;
        for (int i = 0; i < N; ++i) {
            max = Math.max(max, dp[i]);
        }
        bw.write(Integer.toString(max));

        bw.flush();
        bw.close();
        br.close();
    }
}
