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
    static Integer[][] mem;

    public static int dp(int depth, int i) {
        if (depth == N - 1) {
            return arr[depth][i];
        }
        if (mem[depth][i] == null) {
            mem[depth][i] = Math.max(dp(depth + 1, i), dp(depth + 1, i + 1));
        }
        return arr[depth][i] + mem[depth][i];
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N][N];
        mem = new Integer[N][N];
        for (int i = 0; i < N; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; ++j) {
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        bw.write(Integer.toString(dp(0, 0)));

        bw.close();
        br.close();
    }
}
