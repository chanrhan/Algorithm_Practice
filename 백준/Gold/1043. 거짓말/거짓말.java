import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int friendCount;
    static Set<Integer> friends = new HashSet<>();
    static Map<Integer, Set<Integer>> parties = new HashMap<>();
    static Map<Integer, Set<Integer>> members = new HashMap<>();
    static boolean[] visited;

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[M + 1];

        tokenizer = new StringTokenizer(br.readLine());
        friendCount = Integer.parseInt(tokenizer.nextToken());

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < friendCount; ++i) {
            friends.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int size, n;
        for (int m = 1; m <= M; ++m) {
            tokenizer = new StringTokenizer(br.readLine());
            size = Integer.parseInt(tokenizer.nextToken());
            Set<Integer> set = new HashSet<>();
            Set<Integer> ms;
            for (int i = 0; i < size; ++i) {
                n = Integer.parseInt(tokenizer.nextToken());
                ms = members.getOrDefault(n, new HashSet<>());
                ms.add(m);
                members.put(n, ms);
                set.add(n);
            }
            parties.put(m, set);
        }

        int result = 0, count, party;
        for (int f : friends) {
            if (members.containsKey(f)) {
                deque.addAll(members.get(f));
            }
        }

        while (!deque.isEmpty()) {
            party = deque.pop();
            if (visited[party]) {
                continue;
            }
            visited[party] = true;
            ++result;
            for (Integer person : parties.get(party)) {
                deque.addAll(members.get(person));
            }
        }

        bw.write(Integer.toString(M - result));

        bw.close();
        br.close();
    }
}
