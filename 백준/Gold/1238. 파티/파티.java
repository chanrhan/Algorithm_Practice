import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, X;
    static int[][] graph;
    static final int MAX_VALUE = 100_000;

    public static void floyd() {
        for (int k = 1; k <= N; ++k) {
            for (int r = 1; r <= N; ++r) {
                for (int c = 1; c <= N; ++c) {
                    graph[r][c] = Math.min(graph[r][c], graph[k][c] + graph[r][k]);
                }
            }
        }
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        X = Integer.parseInt(tokenizer.nextToken());

        graph = new int[N + 1][N + 1];
        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                if (r == c) {
                    graph[r][c] = 0;
                } else {
                    graph[r][c] = MAX_VALUE;
                }
            }
        }

        int a, b, c;
        for (int i = 0; i < M; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());
            c = Integer.parseInt(tokenizer.nextToken());
            graph[a][b] = c;
        }

        floyd();

        int max = 0;
        for (int r = 1; r <= N; ++r) {
            max = Math.max(max, graph[r][X] + graph[X][r]);
        }
        bw.write(Integer.toString(max));

        bw.close();
        br.close();
    }

}
