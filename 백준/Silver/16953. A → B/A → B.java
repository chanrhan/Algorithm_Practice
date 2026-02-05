import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int bfs(int A, int B) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(B);

        int curr, size, ans = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size > 0) {
                curr = queue.poll();
                --size;
                if (set.contains(curr)) {
                    continue;
                }
                set.add(curr);
                if (curr == A) {
                    return ans + 1;
                }
                if (curr % 2 != 0 && curr % 10 != 1) {
                    return -1;
                }
                if (curr % 2 == 0) {
                    queue.add(curr / 2);
                }
                if (curr % 10 == 1) {
                    queue.add((curr - 1) / 10);
                }
            }
            ++ans;
        }
        return -1;
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        final int A = Integer.parseInt(tokenizer.nextToken());
        final int B = Integer.parseInt(tokenizer.nextToken());

        bw.write(Integer.toString(bfs(A, B)));

        bw.close();
        br.close();
    }
}
