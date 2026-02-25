import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int A;
    static long B;
    static int[][] origin;

    public static int[][] pow(int[][] mat, long b) {
        if (b == 1L) {
            return mat;
        }
        int[][] ret = pow(mat, b / 2);

        ret = mux(ret, ret);
        if (b % 2 == 1L) {
            ret = mux(ret, origin);
        }
        return ret;
    }

    public static int[][] mux(int[][] m1, int[][] m2) {
        int[][] m = new int[A][A];
        for (int r = 0; r < A; ++r) {
            for (int c = 0; c < A; ++c) {
                for (int k = 0; k < A; ++k) {
                    m[r][c] += m1[r][k] * m2[k][c];
                    m[r][c] %= 1000;
                }
            }
        }
        return m;
    }

    public static void main(String[] _s) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        A = Integer.parseInt(tokenizer.nextToken());
        B = Long.parseLong(tokenizer.nextToken());

        origin = new int[A][A];
        for (int r = 0; r < A; ++r) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int c = 0; c < A; ++c) {
                origin[r][c] = Integer.parseInt(tokenizer.nextToken()) % 1000;
            }
        }

        int[][] result = pow(origin, B);
        for (int r = 0; r < A; ++r) {
            for (int c = 0; c < A; ++c) {
                sb.append(result[r][c]).append(' ');
            }
            sb.append('\n');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}
