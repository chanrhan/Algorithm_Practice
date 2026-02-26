import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, R;
    static int[] items;
    static int[][] graph;
    static final int MAX_VALUE = Integer.MAX_VALUE / 2;

    public static void floyd() {
        for (int k = 1; k <= N; ++k) {
            for (int r = 1; r <= N; ++r) {
                for (int c = 1; c <= N; ++c) {
                    graph[r][c] = Math.min(graph[r][c], graph[r][k] + graph[k][c]);
                }
            }
        }
    }

    public static void main(String[] _s) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        R = Integer.parseInt(tokenizer.nextToken());

        graph = new int[N + 1][N + 1];
        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                if (r == c) {
                    graph[r][c] = 0;
                    continue;
                }
                graph[r][c] = MAX_VALUE;
            }
        }
        items = new int[N + 1];

        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            items[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int a, b, l;
        for (int i = 0; i < R; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());
            l = Integer.parseInt(tokenizer.nextToken());
            graph[a][b] = l;
            graph[b][a] = l;
        }

        floyd();
        int max = Integer.MIN_VALUE;
        for (int r = 1; r <= N; ++r) {
            int sum = 0;
            for (int c = 1; c <= N; ++c) {
                if (graph[r][c] != MAX_VALUE && graph[r][c] <= M) {
                    sum += items[c];
                }
            }
            max = Math.max(max, sum);
        }
        bw.write(Integer.toString(max));

        bw.close();
        br.close();
    }

}
