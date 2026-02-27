import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int TC, N, M, W;
    static List<Edge> graph = null;
    static final int INF = 987654321;

    public static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static boolean bellman() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 0);

        for (int i = 0; i < N; ++i) {
            for (Edge next : graph) {
//                if (dist[next.start] == INF) {
//                    continue;
//                }
                if (dist[next.start] + next.weight < dist[next.end]) {
                    dist[next.end] = dist[next.start] + next.weight;
                    if (i == N - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] _s) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(tokenizer.nextToken());
        int s, e, t;
        for (; TC > 0; --TC) {
            tokenizer = new StringTokenizer(br.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            W = Integer.parseInt(tokenizer.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i < M; ++i) {
                tokenizer = new StringTokenizer(br.readLine());
                s = Integer.parseInt(tokenizer.nextToken());
                e = Integer.parseInt(tokenizer.nextToken());
                t = Integer.parseInt(tokenizer.nextToken());
                graph.add(new Edge(s, e, t));
                graph.add(new Edge(e, s, t));
            }
            for (int i = 0; i < W; ++i) {
                tokenizer = new StringTokenizer(br.readLine());
                s = Integer.parseInt(tokenizer.nextToken());
                e = Integer.parseInt(tokenizer.nextToken());
                t = Integer.parseInt(tokenizer.nextToken());
                graph.add(new Edge(s, e, -t));
            }
            sb.append(bellman() ? "YES" : "NO").append('\n');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

}
