import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] graph;
    static final int MAX_VALUE = Integer.MAX_VALUE / 2;

    public static void main(String[] _s) throws Exception {
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                if (r == c) {
                    graph[r][c] = 0;
                } else {
                    graph[r][c] = MAX_VALUE;
                }
            }
        }

        String line;
        for (int r = 0; r < N; ++r) {
            line = br.readLine();
            for (int c = 0; c < N; ++c) {
                if (line.charAt(c) == 'Y') {
                    graph[r][c] = graph[c][r] = 1;
                }
            }
        }

        for (int k = 0; k < N; ++k) {
            for (int r = 0; r < N; ++r) {
                for (int c = 0; c < N; ++c) {
                    graph[r][c] = Math.min(graph[r][c], graph[r][k] + graph[k][c]);
                }
            }
        }

        int sum, max = Integer.MIN_VALUE;
        for (int r = 0; r < N; ++r) {
            sum = 0;
            for (int c = 0; c < N; ++c) {
                if (graph[r][c] == 1 || graph[r][c] == 2) {
                    ++sum;
                }
            }
            max = Math.max(max, sum);
        }
        bw.write(Integer.toString(max));

        bw.close();
        br.close();
    }
}

