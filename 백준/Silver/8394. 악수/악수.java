import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 2];
        arr[1] = 1;
        arr[2] = 2;

        if (N > 2) {
            for (int i = 3; i <= N; ++i) {
                arr[i] = (arr[i - 1] + arr[i - 2]) % 10;
            }
        }
        bw.write(Integer.toString(arr[N]));

        bw.close();
        br.close();
    }
}

