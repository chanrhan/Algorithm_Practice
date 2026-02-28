import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] arr;
    static Integer[][] mem;

    public static int dp(int d, int color) {
        if (d == N - 1) {
            return arr[d][color];
        }
        if (mem[d][color] == null) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 3; ++i) {
                if (i != color) {
                    min = Math.min(min, dp(d + 1, i));
                }
            }
            mem[d][color] = min + arr[d][color];
        }
        return mem[d][color];
    }

    public static void main(String[] _s) throws Exception {
        StringTokenizer tokenizer = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        mem = new Integer[N][3];
        for (int i = 0; i < N; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(tokenizer.nextToken());
            arr[i][1] = Integer.parseInt(tokenizer.nextToken());
            arr[i][2] = Integer.parseInt(tokenizer.nextToken());
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; ++i) {
            min = Math.min(min, dp(0, i));
        }
        bw.write(Integer.toString(min));

        bw.flush();
        bw.close();
        br.close();
    }
}
