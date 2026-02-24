import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws IOException {
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int[][] lcs = new int[a.length + 1][b.length + 1];
        lcs[0][0] = 0;

        int max = Integer.MIN_VALUE;
        for (int r = 1; r <= a.length; ++r) {
            for (int c = 1; c <= b.length; ++c) {
                if (a[r - 1] == b[c - 1]) {
                    lcs[r][c] = lcs[r - 1][c - 1] + 1;
                } else {
                    lcs[r][c] = Math.max(lcs[r - 1][c], lcs[r][c - 1]);
                }
            }
        }
        bw.write(Integer.toString(lcs[a.length][b.length]));

        bw.close();
        br.close();
    }
}
