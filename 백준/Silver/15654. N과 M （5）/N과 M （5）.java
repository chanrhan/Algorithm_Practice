import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] arr;
    static int[] ans;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void dfs(int d) throws IOException {
        if (d == M) {
            for (int i = 0; i < M; ++i) {
                sb.append(ans[i] + " ");
            }
            sb.append('\n');
            return;
        }
        for (int i = 0; i < N; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                ans[d] = arr[i];
                dfs(d + 1);
                visited[i] = false;
            }
        }
    }


    public static String print(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N];
        ans = new int[M];
        visited = new boolean[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(arr);

        dfs(0);

        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}
