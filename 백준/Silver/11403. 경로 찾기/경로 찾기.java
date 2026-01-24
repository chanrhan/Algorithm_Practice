import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static int hasPath(int r, int c, Map<Integer, List<Integer>> g) {
//        System.out.printf("[%d -> %d]\n", r, c);
        boolean flag = (r == c);
        int curr;
        boolean[] visited = new boolean[N];
        List<Integer> list;
        Stack<Integer> stack = new Stack<>();
        stack.push(c);

        while (!stack.isEmpty()) {
            curr = stack.pop();
//            System.out.println("curr: " + curr);
            if (curr == r) {
                if (!flag) {
                    return 1;
                } else {
                    flag = false;
                }
            }
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            list = g.get(curr);
            if (list != null) {
                stack.addAll(list);
            }
        }

        return 0;
    }

    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = null;

        N = Integer.parseInt(br.readLine());

        final Map<Integer, List<Integer>> g = new HashMap<>();
        int n;
        List<Integer> list;
        for (int r = 0; r < N; ++r) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; ++c) {
                n = Integer.parseInt(tokenizer.nextToken());
                if (n == 1) {
                    // r -> c
                    if (!g.containsKey(c)) {
                        g.put(c, new ArrayList<>());
                    }
                    list = g.get(c);
                    list.add(r);
                    g.put(c, list);
                }
            }
        }

        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
//                if (r == c) {
//                    bw.write("1 ");
//                    continue;
//                }
                bw.write(Integer.toString(hasPath(r, c, g)) + ' ');
            }
            bw.write('\n');
        }

        bw.close();

        br.close();
    }
}
