import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final Map<Integer, List<Node>> graph = new HashMap<>();
    static int N;
    static int sum = 0;

    public static class Node {
        int v;
        int weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("(%d: %d)", v, weight);
        }
    }

    public static int dfs(int start) {
        boolean[] visited = new boolean[N + 1];
        int max = 0;
        int find = 1;
        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(start, 0));

        Node curr;
        while (!deque.isEmpty()) {
            curr = deque.pollLast();
            if (visited[curr.v]) {
                continue;
            }
            visited[curr.v] = true;
            if (!graph.containsKey(curr.v)) {
                continue;
            }
            int count = 0;
            for (Node next : graph.get(curr.v)) {
                if (!visited[next.v]) {
                    deque.add(new Node(next.v, next.weight + curr.weight));
                    ++count;
                }
            }
            if (count == 0) {
                if (curr.weight > max) {
                    max = curr.weight;
                    find = curr.v;
                }
            }
        }
        sum = max;
        return find;
    }


    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = null;
        N = Integer.parseInt(br.readLine());
        int a, b, c;

        List<Node> nodes;
        for (int i = 0; i < N - 1; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());
            c = Integer.parseInt(tokenizer.nextToken());
            nodes = graph.getOrDefault(a, new ArrayList<>());
            nodes.add(new Node(b, c));
            graph.put(a, nodes);

            nodes = graph.getOrDefault(b, new ArrayList<>());
            nodes.add(new Node(a, c));
            graph.put(b, nodes);
        }

        dfs(dfs(1));

        bw.write(Integer.toString(sum));

        bw.close();
        br.close();
    }
}
