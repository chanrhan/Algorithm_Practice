import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws Exception {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int[] result = new int[2];
        int min = Integer.MAX_VALUE;

        int low = 0, high = N - 1, value;
        while (low < high) {
            value = arr[low] + arr[high];
//            System.out.printf("%d + %d = %d\n", arr[low], arr[high], value);
            if (Math.abs(value) < min) {
                min = Math.abs(value);
                result[0] = arr[low];
                result[1] = arr[high];
            }
            if (value < 0) {
                ++low;
            } else if (value > 0) {
                --high;
            } else {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        bw.write(result[0] + " " + result[1]);

        bw.flush();
        bw.close();
        br.close();
    }

}

