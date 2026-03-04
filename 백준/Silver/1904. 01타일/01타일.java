import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dp;
    static int N;

    public static void main(String[] _s) throws Exception {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 2];
        dp[1] = 1;
        dp[2] = 2;

        if (N > 2) {
            for (int i = 3; i <= N; ++i) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
            }
        }

        bw.write(Integer.toString(dp[N]));

        bw.close();
        br.close();
    }

}

