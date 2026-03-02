import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int V;
    static final Map<Integer, List<Edge>> graph = new HashMap<>();

    public static class Edge {
        int x;
        int cost;

        public Edge(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("(%d : %d)", x, cost);
        }
    }

    public static Edge dfs(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MIN_VALUE);
        Deque<Edge> deque = new ArrayDeque<>();
        deque.addLast(new Edge(start, 0));
        dist[start] = 0;

        Edge curr;
        int maxCost = Integer.MIN_VALUE;
        int target = start;
        while (!deque.isEmpty()) {
            curr = deque.pollLast();

            for (Edge next : graph.get(curr.x)) {
                if (dist[next.x] != Integer.MIN_VALUE) {
                    continue;
                }
                dist[next.x] = dist[curr.x] + next.cost;
                deque.addLast(new Edge(next.x, dist[next.x]));
            }
        }
        for (int i = 1; i <= V; ++i) {
            if (dist[i] > maxCost) {
                maxCost = dist[i];
                target = i;
            }
        }
        return new Edge(target, maxCost);
    }

    public static void main(String[] _s) throws Exception {
        StringTokenizer tokenizer = null;
        V = Integer.parseInt(br.readLine());

        for (int i = 1; i <= V; ++i) {
            graph.put(i, new ArrayList<>());
        }

        int start, end, cost;
        for (int i = 0; i < V; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            start = Integer.parseInt(tokenizer.nextToken());
            while (tokenizer.hasMoreTokens()) {
                end = Integer.parseInt(tokenizer.nextToken());
                if (end == -1) {
                    break;
                }
                cost = Integer.parseInt(tokenizer.nextToken());
                graph.get(start).add(new Edge(end, cost));
            }
        }

        Edge first = dfs(1);
        Edge second = dfs(first.x);
        bw.write(Integer.toString(second.cost));

        bw.flush();
        bw.close();
        br.close();
    }

}
