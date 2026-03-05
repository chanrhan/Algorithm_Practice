import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    static int V, E;
    static final Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] _s) throws Exception {
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        for (int i = 1; i <= V; ++i) {
            graph.put(i, new ArrayList<>());
        }

        StringTokenizer tokenizer = null;
        int s, e;
        for (int i = 0; i < E; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            s = Integer.parseInt(tokenizer.nextToken());
            e = Integer.parseInt(tokenizer.nextToken());
            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        boolean[] visited = new boolean[V + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        visited[1] = true;

        int curr, count = 0;
        while (!deque.isEmpty()) {
            curr = deque.pollLast();
            ++count;

            for (int next : graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    deque.addLast(next);
                }
            }
        }

        bw.write(Integer.toString(count - 1));

        bw.close();
        br.close();
    }
}

