import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int A, B, C;

    public static long solve(int b) {
        if (b == 1) {
            return A % C;
        }
        long tmp = solve(b / 2);

        if (b % 2 == 1) {
            return (tmp * tmp % C) * A % C;
        }
        return (tmp * tmp) % C;
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        A = Integer.parseInt(tokenizer.nextToken());
        B = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        bw.write(Long.toString(solve(B)));

        bw.close();
        br.close();
    }
}
