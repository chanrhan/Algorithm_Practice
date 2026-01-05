import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int N;

    public static int bfs(int start, int target, Map<Integer, List<Integer>> map) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        if (!map.containsKey(start)) {
            return 0;
        }
        int depth = 0;
        queue.addAll(map.get(start));
        int size = 0;
        Integer curr;
        while (!queue.isEmpty()) {
            if (size == 0) {
                ++depth;
//                System.out.printf("depth: %d\n", depth);
                size = queue.size();
            }
//            System.out.printf("[%d-%d] %s\n", start, target, queue.toString());
            curr = queue.poll();
            --size;
            if (curr == target) {
                break;
            }
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            if (map.containsKey(curr)) {
                queue.addAll(map.get(curr));
            }
        }
//        System.out.println("out depth: " + depth);
        return depth;
    }

    public static void printGraph() {
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                System.out.printf("%2d ", graph[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        final Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = null;
        graph = new int[N + 1][N + 1];
        int a, b;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = 1;

            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }
            list = map.get(a);
            list.add(b);
            map.put(a, list);

            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<>());
            }
            list = map.get(b);
            list.add(a);
            map.put(b, list);
        }

        // start, target
        for (int s = 1; s <= N; ++s) {
            for (int t = 1; t <= N; ++t) {
                if (s == t || graph[s][t] != 0) {
                    continue;
                }
                graph[s][t] = graph[t][s] = bfs(s, t, map);
            }
        }
//        printGraph();

        int min = Integer.MAX_VALUE, sum;
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= N; ++i) {
            sum = 0;
            for (int j = 1; j <= N; ++j) {
                sum += graph[i][j];
            }
            if (sum < min) {
                min = sum;
                result = i;
            } else if (sum == min) {
                result = Math.min(result, i);
            }
        }
        bw.write(Integer.toString(result));

        // close
        bw.close();
        br.close();
    }
}
