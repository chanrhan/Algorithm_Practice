import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;

    public static void main(String[] _s) throws Exception {
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int[] dp = new int[N];
        dp[0] = arr[0];

        int max = dp[0];
        for (int i = 1; i < N; ++i) {
            dp[i] = Math.max(arr[i], arr[i] + dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        bw.write(Integer.toString(max));
        bw.close();
        br.close();
    }
}

