import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 1][2];
        int[][] dp = new int[N + 1][6];

        StringTokenizer tokenizer = null;
        for (int i = 1; i <= N; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(tokenizer.nextToken());
            arr[i][1] = Integer.parseInt(tokenizer.nextToken());
        }

        int a, b;
        int grade = 6;
        int max = 0;
        for (int i = 1; i <= N; ++i) {
            a = arr[i][0];
            b = arr[i][1];

            dp[i][a] = dp[i - 1][a] + 1;
            dp[i][b] = dp[i - 1][b] + 1;
        }

        for (int i = 1; i <= N; ++i) {
            for (int k = 1; k <= 5; ++k) {
                if (dp[i][k] > max) {
                    max = dp[i][k];
                    grade = k;
                } else if (dp[i][k] == max && k < grade) {
                    grade = k;
                }
            }
        }
        bw.write(max + " " + grade);
        bw.close();
        br.close();
    }
}

