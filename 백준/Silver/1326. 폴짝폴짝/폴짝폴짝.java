import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] log = new int[N + 1];
        Arrays.fill(log, -1);
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        tokenizer = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(tokenizer.nextToken());
        int end = Integer.parseInt(tokenizer.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        log[start] = 0;

        int curr;
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr == end) {
                break;
            }
            int step = arr[curr];
            for (int next = curr + step; next <= N; next += step) {
                if (log[next] == -1) {
                    log[next] = log[curr] + 1;
                    queue.add(next);
                }
            }
            for (int next = curr - step; next >= 1; next -= step) {
                if (log[next] == -1) {
                    log[next] = log[curr] + 1;
                    queue.add(next);
                }
            }
        }
        bw.write(Integer.toString(log[end]));

        bw.close();
        br.close();
    }
}

