import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] arr;
    static int[] ans;
    static boolean[] visited;
    static Set<String> set = new HashSet<>();

    public static void dfs(int depth) {
        if (depth == M) {
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < M; ++i) {
                sb2.append(ans[i] + " ");
            }
            if (set.contains(sb2.toString())) {
                return;
            }
            set.add(sb2.toString());
            sb.append(sb2).append('\n');
            return;
        }
        for (int i = 0; i < N; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                ans[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        ans = new int[M];
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
