import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Integer[] mem;
    static int N;

    public static int dp(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        if (mem[n] == null) {
            mem[n] = dp(n - 1) + dp(n - 2) + dp(n - 3);
        }
        return mem[n];
    }


    public static void main(String[] _s) throws Exception {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            N = Integer.parseInt(br.readLine());
            mem = new Integer[N + 1];
            sb.append(dp(N)).append('\n');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}

