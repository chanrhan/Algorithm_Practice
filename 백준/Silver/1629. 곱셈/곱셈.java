import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int A, B, C;

    public static long pow(long e) {
        if (e == 1) {
            return A % C;
        }
        long res = pow(e / 2);

        res = res * res;
        if (e % 2 == 1L) { // odd
            return (res % C) * A % C;
        }
        return res % C;
    }

    public static void main(String[] _s) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        A = Integer.parseInt(tokenizer.nextToken());
        B = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        bw.write(Long.toString(pow(B)));

        bw.close();
        br.close();
    }

}
