import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] mem;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void dp(int n, int count) {
        if (count == M) {
            for (int i = 0; i < M; ++i) {
                sb.append(mem[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 0; i < N; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                mem[count] = arr[i];
                dp(i, count + 1);
                mem[count] = 0;
                visited[i] = false;
            }
        }
    }

    public static void main(String[] _s) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        mem = new int[M];

        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(arr);

        dp(-1, 0);
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

}
    