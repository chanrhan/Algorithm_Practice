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
    static int N, M;
    static Map<Integer, List<Node>> graph = new HashMap<>();

    static boolean[] visited;
    static int start, end;

    public static class Node {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("(%d / %d)", index, weight);
        }
    }

    public static int getNearestIndex(int[] dist) {
        int minValue = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 1; i <= dist.length; ++i) {
            if (!visited[i]) {
                if (dist[i] < minValue) {
                    minValue = dist[i];
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = null;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int a, d, f;
        visited = new boolean[N + 1];
        List<Node> list;
        for (int i = 0; i < M; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(tokenizer.nextToken());
            d = Integer.parseInt(tokenizer.nextToken());
            f = Integer.parseInt(tokenizer.nextToken());
            list = graph.getOrDefault(a, new ArrayList<>());
            list.add(new Node(d, f));
            graph.put(a, list);
        }

        tokenizer = new StringTokenizer(br.readLine());
        start = Integer.parseInt(tokenizer.nextToken());
        end = Integer.parseInt(tokenizer.nextToken());

        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
//            dist[i] = graph[start][i] == 0 ? Integer.MAX_VALUE : graph[start][i];
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        queue.add(new Node(start, 0));

        Node curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
//            System.out.printf("%s : %s\n", curr, Arrays.toString(dist));

            if (dist[curr.index] < curr.weight) {
                continue;
            }
            if (!graph.containsKey(curr.index)) {
                continue;
            }
            for (Node next : graph.get(curr.index)) {
                if (dist[next.index] > curr.weight + next.weight) {
                    dist[next.index] = curr.weight + next.weight;
                    queue.offer(new Node(next.index, dist[next.index]));
                }
            }
        }
        bw.write(Integer.toString(dist[end]) + '\n');

        bw.close();
        br.close();
    }
}
