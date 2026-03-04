import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, R, Q;
    static final Map<Integer, List<Integer>> graph = new HashMap<>();
    static int[] dp;
    static boolean[] visited;

    public static int dfs(int n) {
        int sum = 1;
        for (int next : graph.get(n)) {
            if (!visited[next]) {
                visited[next] = true;
                sum += dfs(next);
            }
        }
        return dp[n] = sum;
    }

    public static void main(String[] _s) throws Exception {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        R = Integer.parseInt(tokenizer.nextToken());
        Q = Integer.parseInt(tokenizer.nextToken());

        dp = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; ++i) {
            graph.put(i, new ArrayList<>());
        }

        int u, v;
        for (int i = 0; i < N - 1; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            u = Integer.parseInt(tokenizer.nextToken());
            v = Integer.parseInt(tokenizer.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited[R] = true;
        dfs(R);

        StringBuilder sb = new StringBuilder();
        int q;
        for (int i = 0; i < Q; ++i) {
            q = Integer.parseInt(br.readLine());
            sb.append(dp[q]).append('\n');
        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

}

