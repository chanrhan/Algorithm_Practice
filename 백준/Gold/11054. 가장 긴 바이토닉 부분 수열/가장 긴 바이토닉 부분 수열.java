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
    static int[] seq;
    static Integer[] dp_i;
    static Integer[] dp_d;

    public static int LIS(int n) {
        if (dp_i[n] == null) {
            dp_i[n] = 1;
            for (int i = n - 1; i >= 0; --i) {
                if (seq[i] < seq[n]) {
                    dp_i[n] = Math.max(dp_i[n], LIS(i) + 1);
                }
            }
        }
        return dp_i[n];
    }

    public static int LDS(int n) {
        if (dp_d[n] == null) {
            dp_d[n] = 1;
            for (int i = n + 1; i < N; ++i) {
                if (seq[i] < seq[n]) {
                    dp_d[n] = Math.max(dp_d[n], LDS(i) + 1);
                }
            }
        }
        return dp_d[n];
    }

    public static void main(String[] _s) throws IOException {
        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        dp_i = new Integer[N];
        dp_d = new Integer[N];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            seq[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < N; ++i) {
            LIS(i);
        }

        for (int i = N - 1; i >= 0; --i) {
            LDS(i);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; ++i) {
            max = Math.max(max, dp_i[i] + dp_d[i] - 1);
        }
        bw.write(Integer.toString(max));

        bw.close();
        br.close();
    }

}
