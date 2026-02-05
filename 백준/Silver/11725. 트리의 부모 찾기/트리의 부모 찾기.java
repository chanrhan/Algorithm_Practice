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
    static int N;
    static Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        boolean[] visited = new boolean[N + 1];
        int[] ans = new int[N + 1];

        int a, b;
        List<Integer> list;
        for (int i = 0; i < N - 1; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());
            list = map.getOrDefault(a, new ArrayList<>());
            list.add(b);
            map.put(a, list);

            list = map.getOrDefault(b, new ArrayList<>());
            list.add(a);
            map.put(b, list);
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);

        int curr;
        while (!deque.isEmpty()) {
            curr = deque.pollLast();
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            if (map.containsKey(curr)) {
                list = map.get(curr);
                for (int n : list) {
                    if (!visited[n]) {
//                        System.out.printf("%d <= %d\n", n, curr);
                        ans[n] = curr;
                    }
                }
                deque.addAll(map.get(curr));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; ++i) {
            sb.append(ans[i]).append('\n');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}
