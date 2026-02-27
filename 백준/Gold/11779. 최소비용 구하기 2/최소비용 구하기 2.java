import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int start, end;
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static int[] dist;
    static List<Integer> result;

    public static class Edge {
        int x;
        int cost;

        public Edge(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static void dijkstra() {
        dist = new int[N + 1];
        int[] log = new int[N + 1];
        result = new ArrayList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(log, -1);
        dist[start] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.cost, e2.cost));
        queue.add(new Edge(start, 0));

        Edge curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();

            if (curr.cost > dist[curr.x]) {
                continue;
            }
            if (!graph.containsKey(curr.x)) {
                continue;
            }
            for (Edge next : graph.get(curr.x)) {
                if (curr.cost + next.cost < dist[next.x]) {
                    dist[next.x] = curr.cost + next.cost;
                    log[next.x] = curr.x;
                    queue.add(new Edge(next.x, dist[next.x]));
                }
            }
        }

        for (int i = end; i != -1; i = log[i]) {
            result.add(i);
        }
        Collections.reverse(result);
    }

    public static void main(String[] _s) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = null;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; ++i) {
            graph.put(i, new ArrayList<>());
        }

        int s, e, t;
        for (int i = 0; i < M; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            s = Integer.parseInt(tokenizer.nextToken());
            e = Integer.parseInt(tokenizer.nextToken());
            t = Integer.parseInt(tokenizer.nextToken());
            graph.get(s).add(new Edge(e, t));
        }

        tokenizer = new StringTokenizer(br.readLine());
        start = Integer.parseInt(tokenizer.nextToken());
        end = Integer.parseInt(tokenizer.nextToken());

        dijkstra();
        sb.append(dist[end]).append('\n')
                .append(result.size())
                .append('\n');
        for (int i = 0; i < result.size(); ++i) {
            sb.append(result.get(i)).append(' ');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

}
