import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, E;
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static int[] v = new int[2];

    public static class Edge {
        int v;
        int cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("(%d: %d)", v, cost);
        }
    }

    public static int dijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        queue.add(new Edge(start, 0));
        dist[start] = 0;

        Edge curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            // 방문하지 않은 정점 방문
            if (dist[curr.v] < curr.cost) {
                continue;
            }

            if (!graph.containsKey(curr.v)) {
                continue;
            }

            // 현재 정점과 연결된 정점들의 비용 배열(dist)을 갱신
            for (Edge e : graph.get(curr.v)) {
                if (e.cost + curr.cost < dist[e.v]) { // 최소가 되는 경우에만 갱신
                    dist[e.v] = e.cost + curr.cost;
                    queue.add(new Edge(e.v, dist[e.v]));
                }
            }
        }
        if (dist[end] == Integer.MAX_VALUE) {
            throw new NumberFormatException();
        }
        return dist[end];
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        E = Integer.parseInt(tokenizer.nextToken());

        int a, b, c;
        List<Edge> edges;
        for (int i = 0; i < E; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());
            c = Integer.parseInt(tokenizer.nextToken());
            edges = graph.getOrDefault(a, new ArrayList<>());
            edges.add(new Edge(b, c));
            graph.put(a, edges);

            edges = graph.getOrDefault(b, new ArrayList<>());
            edges.add(new Edge(a, c));
            graph.put(b, edges);
        }
        tokenizer = new StringTokenizer(br.readLine());
        v[0] = Integer.parseInt(tokenizer.nextToken());
        v[1] = Integer.parseInt(tokenizer.nextToken());

        try {
            int sum1 = 0;
            sum1 += dijkstra(1, v[0]);
            sum1 += dijkstra(v[0], v[1]);
            sum1 += dijkstra(v[1], N);

            int sum2 = 0;
            sum2 += dijkstra(1, v[1]);
            sum2 += dijkstra(v[1], v[0]);
            sum2 += dijkstra(v[0], N);

            bw.write(Integer.toString(Math.min(sum1, sum2)));
        } catch (NumberFormatException e) {
            bw.write("-1");
        }

        bw.close();
        br.close();
    }
}
