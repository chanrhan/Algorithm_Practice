import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, S;
    static int[] arr;

    public static void main(String[] _s) throws Exception {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        S = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int left = 0, right = 0;
        int sum = arr[0];
        int min = Integer.MAX_VALUE;
        while (true) {
            if (sum < S) {
                ++right;
                if (right >= N) {
                    break;
                }
                sum += arr[right];
            } else {
                min = Math.min(min, right - left + 1);
                sum -= arr[left];
                ++left;
            }
        }
        if (min == Integer.MAX_VALUE) {
            min = 0;
        }
        bw.write(Integer.toString(min));

        bw.close();
        br.close();
    }
}

