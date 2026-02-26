import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K;
    static final int LIMIT = 100_001;

    public static void main(String[] _s) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        int[] time = new int[LIMIT];
        Arrays.fill(time, Integer.MAX_VALUE);
        queue.add(N);
        time[N] = 0;

        int curr, count = 0;
        while (!queue.isEmpty()) {
            curr = queue.poll();

            if (curr == K) {
                ++count;
                continue;
            }
            if (curr + 1 < LIMIT && time[curr + 1] > time[curr]) {
                time[curr + 1] = time[curr] + 1;
                queue.add(curr + 1);
            }
            if (curr * 2 < LIMIT && time[curr * 2] > time[curr]) {
                time[curr * 2] = time[curr] + 1;
                queue.add(curr * 2);
            }
            if (curr - 1 >= 0 && time[curr - 1] > time[curr]) {
                time[curr - 1] = time[curr] + 1;
                queue.add(curr - 1);
            }
        }
        sb.append(time[K]).append('\n').append(count);
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

}
