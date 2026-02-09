import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] arr;
    static int[][] maxDp;
    static int[][] minDp;

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N + 1][3];
        maxDp = new int[N + 1][3];
        minDp = new int[N + 1][3];
        for (int i = 1; i <= N; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(tokenizer.nextToken());
            arr[i][1] = Integer.parseInt(tokenizer.nextToken());
            arr[i][2] = Integer.parseInt(tokenizer.nextToken());
        }

        minDp[1][0] = maxDp[1][0] = arr[1][0];
        minDp[1][1] = maxDp[1][1] = arr[1][1];
        minDp[1][2] = maxDp[1][2] = arr[1][2];

        for (int i = 1; i <= N; ++i) {
            maxDp[i][0] = arr[i][0] + Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
            maxDp[i][1] = arr[i][1] + Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2]));
            maxDp[i][2] = arr[i][2] + Math.max(maxDp[i - 1][1], maxDp[i - 1][2]);

            minDp[i][0] = arr[i][0] + Math.min(minDp[i - 1][0], minDp[i - 1][1]);
            minDp[i][1] = arr[i][1] + Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2]));
            minDp[i][2] = arr[i][2] + Math.min(minDp[i - 1][1], minDp[i - 1][2]);
        }
        int max = Math.max(maxDp[N][0], Math.max(maxDp[N][1], maxDp[N][2]));
        int min = Math.min(minDp[N][0], Math.min(minDp[N][1], minDp[N][2]));

        bw.write(max + " " + min);

        bw.close();
        br.close();
    }
}
