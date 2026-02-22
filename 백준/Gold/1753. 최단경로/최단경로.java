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
    static int V, E;
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static int start;

    public static class Edge {
        int v;
        int weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        V = Integer.parseInt(tokenizer.nextToken());
        E = Integer.parseInt(tokenizer.nextToken());
        start = Integer.parseInt(br.readLine());

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
        }

        int[] dist = new int[V + 1];
        for (int i = 1; i <= V; ++i) {
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Edge> queue = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.weight, v2.weight));
        queue.add(new Edge(start, 0));
        dist[start] = 0;

        Edge curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (dist[curr.v] < curr.weight) {
                continue;
            }
            if (!graph.containsKey(curr.v)) {
                continue;
            }
            for (Edge next : graph.get(curr.v)) {
                if (next.weight + curr.weight < dist[next.v]) {
                    dist[next.v] = next.weight + curr.weight;
                    queue.add(new Edge(next.v, dist[next.v]));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; ++i) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(dist[i]);
            }
            sb.append('\n');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}
