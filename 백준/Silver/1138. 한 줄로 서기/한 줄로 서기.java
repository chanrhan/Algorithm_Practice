import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int MAX_VALUE = 11;

    public static void main(String[] _s) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] result = new int[N];
        Arrays.fill(result, MAX_VALUE);
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int leftCount;
        for (int i = 1; i <= N; ++i) {
            leftCount = arr[i];
            for (int k = 0; k < N; ++k) {
                if (leftCount == 0 && result[k] == MAX_VALUE) {
                    result[k] = i;
                    break;
                }
                if (result[k] > i) {
                    --leftCount;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            sb.append(result[i]).append(' ');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}

