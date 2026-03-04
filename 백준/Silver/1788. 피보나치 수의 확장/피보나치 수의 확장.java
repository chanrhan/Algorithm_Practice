import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws Exception {
        int N = Integer.parseInt(br.readLine());

        int sign = 0;
        int n = Math.abs(N);

        int[] arr = new int[n + 1];
        arr[0] = 0;

        if (N != 0) {
            sign = (N > 0 || N % 2 != 0) ? 1 : -1;
            arr[1] = 1;
            for (int i = 2; i <= n; ++i) {
                arr[i] = (arr[i - 1] + arr[i - 2]) % 1_000_000_000;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(sign).append('\n').append(arr[n]);
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

}

