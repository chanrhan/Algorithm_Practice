import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int S;
    static int B;
    static int[] lines = new int[101];
    static boolean[] visited = new boolean[101];

    public static int play() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        int curr, move, size;
        int rollCount = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            ++rollCount;
            while (size > 0) {
                curr = queue.poll();
                --size;
                for (int d = 1; d <= 6; ++d) {
                    move = curr + d;
                    if (move == 100) {
                        return rollCount;
                    }
                    if (visited[move]) {
                        continue;
                    }
                    visited[move] = true;
                    if (lines[move] != 0) {
                        queue.add(lines[move]);
                    } else {
                        queue.add(move);
                    }
                }
            }
        }
        return rollCount;
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        S = Integer.parseInt(tokenizer.nextToken());
        B = Integer.parseInt(tokenizer.nextToken());

        int start, end;
        for (int s = 0; s < S + B; ++s) {
            tokenizer = new StringTokenizer(br.readLine());
            start = Integer.parseInt(tokenizer.nextToken());
            end = Integer.parseInt(tokenizer.nextToken());
            lines[start] = end;
        }

        bw.write(Integer.toString(play()));

        br.close();
        bw.close();
    }
}