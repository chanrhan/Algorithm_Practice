import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws Exception {
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int alen = A.length;
        int blen = B.length;

        int[][] dp = new int[alen + 1][blen + 1];

        for (int r = 1; r <= alen; ++r) {
            for (int c = 1; c <= blen; ++c) {
                if (A[r - 1] == B[c - 1]) {
                    dp[r][c] = dp[r - 1][c - 1] + 1;
                } else {
                    dp[r][c] = Math.max(dp[r][c - 1], dp[r - 1][c]);
                }
            }
        }
        bw.write(Integer.toString(dp[alen][blen]));
        bw.flush();
        bw.close();
        br.close();
    }

}
