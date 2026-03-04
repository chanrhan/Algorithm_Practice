import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;
    static Integer[][] dp;

    public static int dp(int n, int jump) {
        if (n < 0) {
            return Integer.MIN_VALUE;
        }
        if (n == 0) {
            return arr[0];
        }
        if (dp[n][jump] == null) {
            int max = 0;
            if (jump == 1) {
                max = Math.max(max, dp(n - 1, 0));
            }
            max = Math.max(max, dp(n - 2, 1));
            dp[n][jump] = max + arr[n];
        }
        return dp[n][jump];
    }

    public static void main(String[] _s) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new Integer[N + 1][2];
        for (int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp(N, 1);
        bw.write(Integer.toString(dp[N][1]));

        bw.close();
        br.close();
    }
}

