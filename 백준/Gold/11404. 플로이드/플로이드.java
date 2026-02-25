import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int V, E;
    static int[][] graph;
    static final int MAX_VALUE = Integer.MAX_VALUE / 2;

    public static void main(String[] _s) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = null;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        graph = new int[V + 1][V + 1];

        for (int r = 1; r <= V; ++r) {
            for (int c = 1; c <= V; ++c) {
                if (r == c) {
                    graph[r][c] = 0;
                } else {
                    graph[r][c] = MAX_VALUE;
                }
            }
        }

        int s, e, cost;
        for (int i = 0; i < E; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            s = Integer.parseInt(tokenizer.nextToken());
            e = Integer.parseInt(tokenizer.nextToken());
            cost = Integer.parseInt(tokenizer.nextToken());
            graph[s][e] = Math.min(graph[s][e], cost);
        }

        for (int k = 1; k <= V; ++k) {
            for (int r = 1; r <= V; ++r) {
                for (int c = 1; c <= V; ++c) {
                    graph[r][c] = Math.min(graph[r][c], graph[r][k] + graph[k][c]);
                }
            }
        }

        for (int r = 1; r <= V; ++r) {
            for (int c = 1; c <= V; ++c) {
                if (graph[r][c] == MAX_VALUE) {
                    sb.append('0');
                } else {
                    sb.append(graph[r][c]);
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

}
